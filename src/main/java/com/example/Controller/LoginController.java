package com.example.Controller;

import com.example.entity.*;
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
        String u_name = user.getName();
        String password = user.getPassword();

        User admin = adminService.login((Admin)user);
        if (admin != null){
            return ApiResultHandler.success("登录成功",admin);
        }

        Teacher teacher = teacherService.login((Teacher) user);
        if (teacher != null){
            return ApiResultHandler.success("登录成功",teacher);
        }

        Student student = studentService.login((Student) user);
        if (student != null){
            return ApiResultHandler.success("登录成功",user);
        }
        return ApiResultHandler.buildApiResult(500,"登录失败",null);
    }



}
