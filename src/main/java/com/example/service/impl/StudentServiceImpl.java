package com.example.service.impl;

import com.example.entity.Student;
import com.example.exception.CustomerException;
import com.example.service.StudentService;
import com.example.utils.TakenUtils;
import com.example.mapper.StudentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    StudentMapper studentMapper;

    //学生登陆
    @Override
    public Student login(Student student) {
        Student dbStudent = studentMapper.selectByUsername(student.getUsername());
        if(dbStudent == null){
            throw new CustomerException("账号不存在！");
        }

        if(!dbStudent.getPassword().equals(student.getPassword())){
            throw new CustomerException("账号或密码错误");
        }

        String token = TakenUtils.createToken(dbStudent.getId() + "-" + "STUDENT",dbStudent.getPassword());
        dbStudent.setToken(token);
        return dbStudent;
    }
}
