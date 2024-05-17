package com.example.EntityMapping.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "foreignKey_add_id")  //to add a column name in the table
//    private Address address;

    //one to Many mapping
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "foreignKey_add_id",referencedColumnName = "id")
    private List<Address> address;
}
