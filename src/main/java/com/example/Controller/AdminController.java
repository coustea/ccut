package com.example.Controller;

import com.example.entity.Admin;
import com.example.entity.ApiResult;
import com.example.entity.Student;
import com.example.mapper.AdminMapper;
import com.example.service.AdminService;
import com.example.utils.ApiResultHandler;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @GetMapping
    public ApiResult<List<Admin>> getAllStudent(@ModelAttribute Admin admin) {
        try {
            if (admin == null) {
                return ApiResultHandler.success("查询成功", adminService.selectAll(null));
            }
            return ApiResultHandler.success("查询成功",adminService.selectAll(admin));
        }catch (Exception e){
            return ApiResultHandler.buildApiResult(500,"查询失败",null);
        }
    }

    @PutMapping("/{id}")
    public ApiResult update(@RequestBody Admin admin) {
        int res = adminService.updateAdmin(admin);
        if (res == 1){
            return ApiResultHandler.buildApiResult(200,"更新成功",admin);
        }
        else {
            return ApiResultHandler.buildApiResult(400,"更新失败",null);
        }
    }


    @DeleteMapping("/{id}")
    public ApiResult delete(@PathVariable Integer id) {
        int res = adminService.deleteAdmin(id);
        if (res == 1){
            return ApiResultHandler.buildApiResult(200, "删除成功", null);
        }else {
            return ApiResultHandler.buildApiResult(400,"删除失败",null);
        }

    }


    @PostMapping
    public ApiResult add(@RequestBody Admin admin){
        int res = adminService.addAdmin(admin);
        if (res == 1){
            return ApiResultHandler.buildApiResult(200,"添加成功",admin);
        }
        else {
            return ApiResultHandler.buildApiResult(400,"添加失败",null);
        }
    }



}