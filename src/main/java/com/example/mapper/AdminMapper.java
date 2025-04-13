package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {

    @Select("select * from `admin` where username = #{username}")
    Admin selectByUsername(String username);

    void insert(Admin admin);

    @Delete("delete  from `admin` where id = #{id}")
    void delete(Admin admin);

    void updateById(Admin admin);

    List<Admin> selectAll(Admin admin);
}
