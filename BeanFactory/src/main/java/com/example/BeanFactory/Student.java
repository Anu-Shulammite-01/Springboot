package com.example.BeanFactory;

public class Student {
    private String name;
    private String age;

    //constructor 1
    public Student(){
    }

    //constructor 2
    public Student(String name, String age){
        this.name = name;
        this.age = age;
    }

    //Method
    public String toString(){
        return "Student {name = '" + name + "', age = '" + age + "'}";
    }
}
