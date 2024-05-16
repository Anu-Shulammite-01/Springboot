package com.example.RestEx.controller;

import com.example.RestEx.entity.Student;
import com.example.RestEx.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    private StudentService stuService;

    @GetMapping
    public List<Student> getAllStudents(){
        return stuService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id){
        return stuService.getStudentById(id).
                map(student -> ResponseEntity.ok().body(student)).
                orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student){
        return stuService.addStudent(student);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student){
        return stuService.getStudentById(id).
                map(student1 ->{
                    student1.setName(student.getName());
                    Student stu = stuService.updateStudent(student1);
                    return ResponseEntity.ok().body(stu);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id){
        return stuService.getStudentById(id).map(student -> {
            stuService.deleteStudent(id);
            return ResponseEntity.ok().body(student);
        }).orElse(ResponseEntity.notFound().build());
    }
}
