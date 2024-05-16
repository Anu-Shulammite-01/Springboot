package com.example.REST.service;

import com.example.REST.entity.Employee;
import com.example.REST.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    //Get all employees
    public List<Employee> getAllEmployees(){
        List<Employee> emp = (List<Employee>) empRepo.findAll();
        return emp;
    }

    //Get Employee by id
    public Optional<Employee> getEmployeeById(Integer id){
        return empRepo.findById(id);
    }

    //create
    public Employee addEmployee(Employee emp){
        return empRepo.save(emp);
    }

    //update
    public Employee updateEmployee(Employee emp){
        return empRepo.save(emp);
    }

    //delete
    public void deleteById(Integer id){
        empRepo.deleteById(id);
    }

}
