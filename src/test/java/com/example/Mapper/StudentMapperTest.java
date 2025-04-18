package com.example.Mapper;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @Test
    void insert(){
        Student student = new Student();
        student.setUsername("张三");
        student.setPassword("123");
        student.setAcademe("计算机学院");
        student.setClassname("软件工程二班");
        student.setName("张三");
        student.setPhone("13800138000");
        student.setRole("student");
        int result = studentMapper.insert(student);
        if (result == 1){
            System.out.println("success");
        }else {
            System.out.println("failed");
        }
    }


    @Test
    void selectAll(){
        List<Student> students = studentService.selectAll(null);
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
