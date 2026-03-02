package com.example.demo.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.Response;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 添加学生
    @PostMapping
    public Response<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO saved = studentService.addStudent(studentDTO);
        return Response.newSuccess(saved);
    }
}
