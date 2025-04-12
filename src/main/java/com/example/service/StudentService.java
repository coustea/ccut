package com.example.service;

import com.example.entity.Student;

public interface StudentService {
    // 学生登陆
    Student login(Student student);
    // 添加学生信息
    void addStudent(Student student);
    // 删除学生信息
    void deleteStudent(Integer id);
    // 修改学生信息
    void updateStudent(Student student);
}
