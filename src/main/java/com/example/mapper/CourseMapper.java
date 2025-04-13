package com.example.mapper;

import com.example.entity.Course;

import java.util.List;

public interface CourseMapper {

    void insert(Course course);

    void update(Course course);

    void deleteById(Integer id);

    Course findById(Integer id);

    List<Course> findAll();
}