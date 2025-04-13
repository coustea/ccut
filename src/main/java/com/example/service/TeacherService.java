package com.example.service;

import com.example.entity.Teacher;
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
    public Teacher login(Teacher teacher) {
        Teacher dbTeacher = teacherMapper.selectByUsername(teacher.getUsername());
        if(dbTeacher == null){
            throw new CustomerException("账号不存在！");
        }

        if(!dbTeacher.getPassword().equals(teacher.getPassword())){
            throw new CustomerException("账号或密码错误");
        }

        String token = TakenUtils.createToken(dbTeacher.getId() + "-" + "STUDENT",dbTeacher.getPassword());
        dbTeacher.setToken(token);
        return dbTeacher;
    }
    //添加教师
    public void addTeacher(Teacher teacher) {
        teacherMapper.insert(teacher);
    }
    //删除教师
    public void deleteTeacher(Integer id) {
        teacherMapper.delete(id);
    }
    //修改教师
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateById(teacher);

    }
    //分页查询
    public PageInfo<Teacher> selectPage(Integer pageNum, Integer pageSize, Teacher teacher) {
        PageHelper.startPage(pageNum,pageSize);
        List<Teacher> list = teacherMapper.selectAll(teacher);
        return PageInfo.of(list);
    }
}
