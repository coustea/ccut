package com.example.Controller;

import com.example.entity.ApiResult;
import com.example.entity.Student;
import com.example.service.impl.StudentServiceImpl;
import com.example.utils.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @PutMapping("/{id}")
    public ApiResult updateStudent(@PathVariable Integer id,@RequestBody Student student){
        if (student == null){
            return ApiResultHandler.buildApiResult(400,"请求失败",null);
        }
        else {
            studentService.updateStudent(student);
            return ApiResultHandler.buildApiResult(200,"请求成功",student);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResult deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ApiResultHandler.buildApiResult(200,"请求成功",null);
    }

}
