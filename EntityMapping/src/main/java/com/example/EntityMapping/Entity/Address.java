package com.example.EntityMapping.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addId;
    private String city;
    private String addressType;

//    //This is for bidirectional one!
//    @OneToOne(mappedBy = "address",cascade = CascadeType.ALL)
//    @JsonIgnore //prevent circular reference
//    private Employee employee;
}
