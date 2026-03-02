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
        System.out.println("Service received DTO: " + dto.getName() + ", " + dto.getEmail());

        // 创建实体
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());

        // 保存到数据库
        Student saved = studentRepository.save(student);

        System.out.println("Saved entity ID: " + saved.getId());

        // 转换为 DTO 返回
        StudentDTO result = new StudentDTO();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setEmail(saved.getEmail());
        result.setAge(saved.getAge());
        return result;
    }

    @Override
public StudentDTO getStudentById(Long id) {
    System.out.println("Service: 查询学生 ID = " + id);

    Student student = studentRepository.findById(id).orElse(null);
    if (student == null) {
        System.out.println("Service: 学生未找到");
        return null;
    }

    StudentDTO dto = new StudentDTO();
    dto.setId(student.getId());
    dto.setName(student.getName());
    dto.setEmail(student.getEmail());
    dto.setAge(student.getAge());

    System.out.println("Service: 查询到学生 " + dto.getName());
    return dto;
}

@Override
public StudentDTO updateStudent(Long id, StudentDTO dto) {
    System.out.println("Service: 更新学生 ID = " + id);

    Student student = studentRepository.findById(id).orElse(null);
    if (student == null) {
        System.out.println("Service: 学生未找到，无法更新");
        return null;
    }

    // 更新字段
    student.setName(dto.getName());
    student.setEmail(dto.getEmail());
    student.setAge(dto.getAge());

    Student updated = studentRepository.save(student);

    StudentDTO result = new StudentDTO();
    result.setId(updated.getId());
    result.setName(updated.getName());
    result.setEmail(updated.getEmail());
    result.setAge(updated.getAge());

    System.out.println("Service: 更新完成，学生 " + result.getName());
    return result;
}

@Override
public StudentDTO deleteStudent(Long id) {
    System.out.println("Service: 删除学生 ID = " + id);

    Student student = studentRepository.findById(id).orElse(null);
    if (student == null) {
        System.out.println("Service: 学生未找到，无法删除");
        return null;
    }

    // 删除学生
    studentRepository.delete(student);
    System.out.println("Service: 学生已删除 " + student.getName());

    // 返回删除的学生信息（可选）
    StudentDTO dto = new StudentDTO();
    dto.setId(student.getId());
    dto.setName(student.getName());
    dto.setEmail(student.getEmail());
    dto.setAge(student.getAge());

    return dto;
}

    
}
