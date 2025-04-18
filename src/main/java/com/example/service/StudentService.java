package com.example.service;

import com.example.entity.Student;
import com.example.entity.User;
import com.example.exception.CustomerException;
import com.example.utils.TakenUtils;
import com.example.mapper.StudentMapper;
import jakarta.annotation.Resource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Resource
    StudentMapper studentMapper;

    //学生登陆
    public Student login(User user) {
        Student dbStudent = studentMapper.selectByUsername(user.getUsername());
        if(dbStudent == null){
            throw new CustomerException("账号不存在！");
        }

        if(!dbStudent.getPassword().equals(user.getPassword())){
            throw new CustomerException("账号或密码错误");
        }

        String token = TakenUtils.createToken(dbStudent.getId() + "-" + "STUDENT",dbStudent.getPassword());
        dbStudent.setToken(token);
        return dbStudent;
    }
    //添加学生
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }
    //删除学生
    public int deleteStudent(Integer id) {

        return studentMapper.deleteById(id);
    }
    //修改学生
    public int updateStudent(Student student) {
       return studentMapper.updateById(student);

    }
    public List<Student> selectAll(Student student) {
        return studentMapper.selectAll(student);
    }
}
