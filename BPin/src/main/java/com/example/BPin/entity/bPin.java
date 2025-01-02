package com.example.BPin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "bPin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class bPin {
        @Id
        private String username;
        private Integer bpin;
        private String udid;
}
