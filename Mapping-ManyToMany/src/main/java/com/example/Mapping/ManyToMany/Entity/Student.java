package com.example.Mapping.ManyToMany.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.util.HashSet;
import java.util.Set;

@Data
@Table(name = "STUDENT_TBL")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @ManyToMany
    @JoinTable(name = "STUDENT_COURSE_TBL",
            joinColumns = {
                    @JoinColumn(name = "student_id",referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id")
            })
    private Set<Courses> courses;
}
