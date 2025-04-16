package com.example.Controller;

import com.example.entity.ApiResult;
import com.example.entity.Student;
import com.example.entity.User;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;


    @PostMapping
    @ResponseBody
    public ApiResult login(@RequestBody User user) {

        if ("admin".equals(user.getRole())) {
            adminService.login(user);
            return ApiResultHandler.buildApiResult(200, "请求成功", user);
        } else if ("teacher".equals(user.getRole())) {
            teacherService.login(user);
            return ApiResultHandler.buildApiResult(200, "请求成功", user);
        } else if ("student".equals(user.getRole())) {
            studentService.login(user);
            return ApiResultHandler.buildApiResult(200, "请求成功", user);
        } else{
            return ApiResultHandler.buildApiResult(400,"请求失败",null);
        }
    }
}
