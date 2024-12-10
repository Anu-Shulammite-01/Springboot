package com.example.JWTAuthenticate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthReq {
    private String username;
    private String password;

    private Integer mpin;

    public AuthReq(String username,String password){
        this.username=username;
        this.password=password;
    }

    public AuthReq(String username,Integer mpin){
        this.username=username;
        this.mpin=mpin;
    }

}
