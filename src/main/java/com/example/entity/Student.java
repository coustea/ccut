package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * 序号
     * */
    private Integer id;


    /**
     * 学号
     * */
//    private String num;

    /**
     * 用户名
     * */
    private String username;


    /**
     * 密码
     * */
    private String password;


    /**
     * 序号
     * */
    private String academe;


    /**
     * 班级
     * */
    private String classname;


    /**
     * 姓名
     * */
    private String name;


    /**
     * 电话号码
     * */
    private String phone;


    /**
     * 角色
     * */
    private String role;


    private String token;
}
