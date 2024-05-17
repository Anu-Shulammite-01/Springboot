package com.example.EntityMapping.Repository;

import com.example.EntityMapping.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
}
