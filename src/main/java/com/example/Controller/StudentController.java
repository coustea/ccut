package com.example.Controller;

import com.example.entity.Admin;
import com.example.entity.ApiResult;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.utils.ApiResultHandler;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;



//    @GetMapping
//    public List<Student> getAllStudent(@ModelAttribute Student student) {
//        if (student == null) {
//           return studentService.selectAll(null);
//        }
//        return studentService.selectAll(student);
//    }
    @GetMapping
    public ApiResult<List<Student>> getAllStudent(@ModelAttribute Student student) {
        try {
            if (student == null) {
                return ApiResultHandler.success("查询成功", studentService.selectAll(null));
            }
            return ApiResultHandler.success("查询成功",studentService.selectAll(student));
        }catch (Exception e){
            return ApiResultHandler.buildApiResult(500,"查询失败",null);
        }
    }

    @PutMapping("/{id}")
    public ApiResult update(@RequestBody Student student) {
        try {
            int res = studentService.updateStudent(student);
            if (res == 1){
                return ApiResultHandler.buildApiResult(200,"更新成功",student);
            }
            else {
                return ApiResultHandler.buildApiResult(500,"更新失败",null);
            }
        } catch (Exception e) {
            return ApiResultHandler.buildApiResult(500,"更新失败",null);
        }

    }


    @DeleteMapping("/{id}")
    public ApiResult delete(@PathVariable Integer id) {
        try {
            int res = studentService.deleteStudent(id);
            if (res == 1){
                return ApiResultHandler.success("删除成功",null);
            }
            else {
                return ApiResultHandler.buildApiResult(500, "删除失败", null);
            }
        } catch (Exception e) {
            return ApiResultHandler.buildApiResult(500, "删除失败", null);
        }
    }



    @PostMapping
    public ApiResult<String> add(@RequestBody Student student){
        try {
            int affectedRows = studentService.addStudent(student);
            if (affectedRows == 1){
                return ApiResultHandler.success("添加成功",student);
            }
            else{
                return ApiResultHandler.buildApiResult(500, "插入失败", null);
            }
        } catch (Exception e) {
            return ApiResultHandler.buildApiResult(500, "插入失败", null);
        }
    }
}