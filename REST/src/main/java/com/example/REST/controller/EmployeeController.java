package com.example.REST.controller;

import com.example.REST.entity.Employee;
import com.example.REST.service.EmployeeService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    //Get all employees
    @GetMapping
    public List<Employee> getAllEmployees(){
        return empService.getAllEmployees();
    }

    //Get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id){
        return empService.getEmployeeById(id)
                .map(emp -> ResponseEntity.ok().body(emp))
                .orElse(ResponseEntity.notFound().build());
    }

    //add a new employee
    @PostMapping("/create")
    public Employee addEmployee(@RequestBody Employee employee){
        return empService.addEmployee(employee);
    }

    //update an employee
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee empDetails){
        return empService.getEmployeeById(id).map(emp ->{
            emp.setName(empDetails.getName());
            emp.setEmail(empDetails.getEmail());

            Employee updatedEmp = empService.updateEmployee(emp);
            return ResponseEntity.ok().body(updatedEmp);
        }).orElse(ResponseEntity.notFound().build());
    }

    //delete an employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id){
        return empService.getEmployeeById(id).map(emp -> {
            empService.deleteById(id);
            return ResponseEntity.ok("Employee Deleted!");
        }).orElse(ResponseEntity.notFound().build());
    }
}
