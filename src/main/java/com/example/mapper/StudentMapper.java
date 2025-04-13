package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from `student` where username = #{username}")
    Student selectByUsername(String username);

    void insert(Student student);

    @Delete("delete  from `student` where id = #{id}")
    void delete(Integer id);

    void updateById(Student student);

    List<Student> selectAll(Student student);

}
