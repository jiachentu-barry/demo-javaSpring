package com.example.demo.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentDTO;
import com.example.demo.service.Response;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/student/test")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // POST 新增学生接口
    @PostMapping
    public Response<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) {
        try {
            // 打印接收到的 DTO，方便调试
            System.out.println("Received DTO: " + studentDTO.getName() + ", " + studentDTO.getEmail());
            
            StudentDTO saved = studentService.addStudent(studentDTO);

            // 打印保存后的 ID
            System.out.println("Saved Student ID: " + saved.getId());

            return Response.newSuccess(saved);
        } catch (Exception e) {
            // 打印完整异常堆栈
            e.printStackTrace();

            // 返回统一错误信息给前端
            return Response.newFail("新增学生失败：" + e.getMessage());
        }
    }
    
    // GET 根据 ID 查询学生
@GetMapping("/{id}")
public Response<StudentDTO> getStudentById(@PathVariable Long id) {
    try {
        StudentDTO student = studentService.getStudentById(id);
        if (student != null) {
            return Response.newSuccess(student);
        } else {
            return Response.newFail("未找到 ID 为 " + id + " 的学生");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return Response.newFail("查询学生失败：" + e.getMessage());
    }
}
    @PutMapping("/{id}")
public Response<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
    try {
        StudentDTO updated = studentService.updateStudent(id, studentDTO);
        if (updated != null) {
            return Response.newSuccess(updated);
        } else {
            return Response.newFail("未找到 ID 为 " + id + " 的学生，更新失败");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return Response.newFail("更新学生失败：" + e.getMessage());
    }

    
}

@DeleteMapping("/{id}")
public Response<StudentDTO> deleteStudent(@PathVariable Long id) {
    try {
        StudentDTO deleted = studentService.deleteStudent(id);
        if (deleted != null) {
            return Response.newSuccess(deleted);
        } else {
            return Response.newFail("未找到 ID 为 " + id + " 的学生，删除失败");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return Response.newFail("删除学生失败：" + e.getMessage());
    }
}
    

    // GET 测试接口
    @GetMapping
    public String test() {
        return "API is running";
    }
}
