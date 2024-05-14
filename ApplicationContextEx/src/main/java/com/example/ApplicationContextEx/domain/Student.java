package com.example.ApplicationContextEx.domain;

public class Student {
    private int id;
    private String name;

    //Constructor1
    public Student(){
    }

    //Constructor 2
    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String toString(){
        return "Student {id = '" + id + "', name = '" + name + "'}";
    }
}
