package com.example.RestEx.service;

import com.example.RestEx.entity.Student;
import com.example.RestEx.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo stuRepo;

    public List<Student> getAllStudents(){
        return stuRepo.findAll();
    }

    public Optional<Student> getStudentById(Integer id){
        return stuRepo.findById(id);
    }

    public Student addStudent(Student student){
        return stuRepo.save(student);
    }

    public Student updateStudent(Student student){
        return stuRepo.save(student);
    }

    public void deleteStudent(Integer id){
        stuRepo.deleteById(id);
    }
}
