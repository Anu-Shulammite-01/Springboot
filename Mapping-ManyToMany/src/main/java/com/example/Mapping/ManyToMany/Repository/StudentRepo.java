package com.example.Mapping.ManyToMany.Repository;

import com.example.Mapping.ManyToMany.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
