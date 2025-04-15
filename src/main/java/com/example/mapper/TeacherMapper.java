package com.example.mapper;

import com.example.entity.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Select("select * from `teacher` where username = #{username}")
    Teacher selectByUsername(String username);

    void insert(Teacher teacher);

    @Delete("delete  from `teacher` where id = #{id}")
    void delete(Integer id);

    void updateById(Teacher teacher);

    List<Teacher> selectAll(Teacher teacher);

    @Select("select * from `teacher` where id = #{id}")
    Teacher selectById(Integer id);

}
