package com.example.service;

import com.example.entity.Course;
import com.example.mapper.CourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Resource
    CourseMapper courseMapper;
    
    //添加课程
    public void addCourse(Course course) {
        courseMapper.insert(course);
    }
    //删除课程
    public void deleteCourse(Integer id) {
        courseMapper.deleteById(id);
    }
    //修改课程
    public void updateCourse(Course course) {
        courseMapper.updateById(course);

    }
    //分页查询 根据课程名、学期、教师姓名模糊查询
    public PageInfo<Course> selectPage(Integer pageNum, Integer pageSize, Course course) {
        PageHelper.startPage(pageNum,pageSize);
        List<Course> list = courseMapper.findAll(course);
        return PageInfo.of(list);
    }
    //根据课程状态查询
    public List<Course> findByStatus(Course.Status status) {
        return courseMapper.findByStatus(status);
    }
}
