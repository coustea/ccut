package com.example.service;

import com.example.entity.Student;
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
    //添加学生
    public void addStudent(Student student) {
        studentMapper.insert(student);
    }
    //删除学生
    public void deleteStudent(Integer id) {
        studentMapper.deleteById(id);
    }
    //修改学生
    public void updateStudent(Student student) {
        studentMapper.updateById(student);

    }
    //分页查询
    public PageInfo<Student> selectPage(Integer pageNum, Integer pageSize,Student student) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> list = studentMapper.selectAll(student);
        return PageInfo.of(list);
    }
}
