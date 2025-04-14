package com.example.mapper;

import com.example.entity.Course;
import com.example.entity.Course.Status;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    int insert(Course course);

    int deleteById(@Param("id") Integer id);

    int updateById(Course course);

    List<Course> findAll(Course course);

    List<Course> findByStatus(@Param("status") Status status);
}