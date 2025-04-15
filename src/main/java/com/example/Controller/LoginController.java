package com.example.Controller;

import com.example.entity.ApiResult;
import com.example.entity.Student;
import com.example.entity.User;
import com.example.service.StudentService;
import com.example.utils.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ApiResult login(@RequestBody Student student){

        if (student == null){
            return ApiResultHandler.buildApiResult(400,"请求失败",null);
        }
        Student student1 = studentService.login(student);
        if (student1 == null){
            return ApiResultHandler.buildApiResult(400,"请求失败",null);
        }
        else {
            return ApiResultHandler.buildApiResult(200,"请求成功",student1);
        }
    }

}
