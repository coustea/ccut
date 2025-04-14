package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {


    int insert(Admin admin);

    @Delete("delete  from `admin` where id = #{id}")
    int delete(Admin admin);

    int updateById(Admin admin);

    @Select("select * from `admin` where username = #{username}")
    Admin selectByUsername(String username);

    List<Admin> selectAll(Admin admin);
}
