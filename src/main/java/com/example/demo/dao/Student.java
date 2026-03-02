package com.example.demo.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "student")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private Integer age;

    public Integer  getId() {return id;}
    public void setId(Integer  id) { this.id = id; }

    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name;}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){this.email = email;}

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) { this.age = age; }
}
