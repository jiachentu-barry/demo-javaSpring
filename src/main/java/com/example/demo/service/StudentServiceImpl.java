package com.example.demo.service;

import org.springframework.stereotype.Service;


import com.example.demo.dao.Student;
import com.example.demo.dao.StudentRepository;
import com.example.demo.dto.StudentDTO;

@Service
public class StudentServiceImpl implements StudentService {

   private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO addStudent(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        Student saved = studentRepository.save(student);

        // 转换为 DTO 返回
        StudentDTO result = new StudentDTO();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setEmail(saved.getEmail());
        return result;
    }


}
