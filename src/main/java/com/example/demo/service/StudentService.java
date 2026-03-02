package com.example.demo.service;


import com.example.demo.dto.StudentDTO;

public interface StudentService {

    StudentDTO addStudent(StudentDTO studentDTO);
    StudentDTO getStudentById(Long id);

     // 新增这行，声明更新方法
    StudentDTO updateStudent(Long id, StudentDTO dto);

    StudentDTO deleteStudent(Long id);
}
