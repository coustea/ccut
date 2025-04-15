package com.example.Controller;

import com.example.entity.ApiResult;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.utils.ApiResultHandler;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PutMapping("/{id}")
    public ApiResult updateStudent(@RequestBody Student student) {
      int res = studentService.updateStudent(student);
      if (res == 1){
          return ApiResultHandler.buildApiResult(200,"更新成功",student);
      }
      else {
          return ApiResultHandler.buildApiResult(400,"更新失败",null);
      }
    }


    @DeleteMapping("/{id}")
    public ApiResult deleteStudent(@PathVariable Integer id) {
        int res = studentService.deleteStudent(id);
        if (res == 1){
            return ApiResultHandler.buildApiResult(200, "删除成功", null);
        }else {
            return ApiResultHandler.buildApiResult(400,"删除失败",null);
        }

    }


    @PostMapping
    public ApiResult addStudent(@RequestBody Student student){
        int res = studentService.addStudent(student);
        if (res == 1){
            return ApiResultHandler.buildApiResult(200,"添加成功",student);
        }
        else {
            return ApiResultHandler.buildApiResult(400,"添加失败",null);
        }
    }

}