package com.example.Mapping.ManyToMany.Controller;

import com.example.Mapping.ManyToMany.Entity.Courses;
import com.example.Mapping.ManyToMany.Entity.Student;
import com.example.Mapping.ManyToMany.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/student")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> stu = studentRepo.findAll();
        return ResponseEntity.ok(stu);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        studentRepo.save(student);
        return ResponseEntity.ok("Student added!");
    }

    @DeleteMapping("/delete")
    public void deleteAllStudents() {
        studentRepo.deleteAll();
    }
}
