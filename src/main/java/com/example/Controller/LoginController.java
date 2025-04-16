package com.example.Controller;

import com.example.entity.*;
import com.example.exception.CustomerException;
import com.example.service.AdminService;
import com.example.service.StudentService;
import com.example.service.TeacherService;
import com.example.utils.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;


    @PostMapping
    public ApiResult login(@RequestBody User user) {

        if("admin".equals(user.getRole())){
            Admin admin = adminService.login(user);
            return ApiResultHandler.success("登录成功",admin);
        }else if("teacher".equals(user.getRole())){
            Teacher teacher = teacherService.login(user);
            return ApiResultHandler.success("登录成功",teacher);
        }else if("student".equals(user.getRole())){
            Student student = studentService.login(user);
            return ApiResultHandler.success("登录成功",student);
        }else{
            return ApiResultHandler.buildApiResult(500,"登录失败",null);
        }
    }

}
