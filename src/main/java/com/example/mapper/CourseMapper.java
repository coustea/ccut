package com.example.mapper;

import com.example.entity.Course;
import com.example.entity.Course.Status;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper {

    int insert(Course course);

    int deleteById(@Param("id") Integer id);

    int updateById(Course course);

    List<Course> findAll(Course course);

    @Select("select * from `course` where id = #{id}")
    Course selectById(Integer id);

    List<Course> findByStatus(@Param("status") Status status);
}