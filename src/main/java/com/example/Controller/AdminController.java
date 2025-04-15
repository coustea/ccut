package com.example.Controller;

import com.example.entity.Admin;
import com.example.entity.ApiResult;
import com.example.service.AdminService;
import com.example.utils.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody Admin admin) {
        if(admin == null){
            return ApiResultHandler.buildApiResult(400, "账号密码不能为空", null);
        }
        Admin admin1 = adminService.login(admin);
        if(admin1 == null){
            return ApiResultHandler.buildApiResult(400, "用户不存在", null);
        } else if (!admin1.getPassword().equals(admin.getPassword())) {
            return ApiResultHandler.buildApiResult(400, "密码错误", null);
        } else {
            return ApiResultHandler.buildApiResult(200, "请求成功", admin1);
        }
    }
}