package com.example.EntityMapping.Controller;

import com.example.EntityMapping.Entity.Employee;
import com.example.EntityMapping.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepo empRepo;

    @PostMapping("/addEmployee")
    public ResponseEntity<String> addEmployee(@RequestBody List<Employee> employee){
        empRepo.saveAll(employee);
        return ResponseEntity.ok("Data Saved!");
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getEmployee(){
        List<Employee> employees = empRepo.findAll();
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllEmployee(){
        empRepo.deleteAll();
        return ResponseEntity.ok("Data deleted!");
    }
}
