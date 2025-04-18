package com.example.service;

import com.example.entity.Teacher;
import com.example.entity.User;
import com.example.exception.CustomerException;
import com.example.mapper.TeacherMapper;
import com.example.utils.TakenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Resource
    TeacherMapper teacherMapper;

    //教师登陆
    public Teacher login(User user) {
        Teacher dbTeacher = teacherMapper.selectByUsername(user.getUsername());
        if(dbTeacher == null){
            throw new CustomerException("账号不存在！");
        }

        if(!dbTeacher.getPassword().equals(user.getPassword())){
            throw new CustomerException("账号或密码错误");
        }

        String token = TakenUtils.createToken(dbTeacher.getId() + "-" + "STUDENT",dbTeacher.getPassword());
        dbTeacher.setToken(token);
        return dbTeacher;
    }
    //添加教师
    public int addTeacher(Teacher teacher) {

        return teacherMapper.insert(teacher);
    }
    //删除教师
    public int deleteTeacher(Integer id) {
       return teacherMapper.delete(id);
    }
    //修改教师
    public int updateTeacher(Teacher teacher) {
       return teacherMapper.updateById(teacher);

    }
    public List<Teacher> selectAll(Teacher teacher) {
        return teacherMapper.selectAll(teacher);
    }
}
