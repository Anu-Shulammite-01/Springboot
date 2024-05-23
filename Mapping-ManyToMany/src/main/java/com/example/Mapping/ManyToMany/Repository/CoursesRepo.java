package com.example.Mapping.ManyToMany.Repository;

import com.example.Mapping.ManyToMany.Entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepo extends JpaRepository<Courses,Integer> {
}
