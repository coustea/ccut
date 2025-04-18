package com.example.Controller;

import com.example.entity.ApiResult;
import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.mapper.TeacherMapper;
import com.example.service.TeacherService;
import com.example.utils.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ApiResult<List<Teacher>> getAllStudent(@ModelAttribute Teacher teacher) {
        try {
            if (teacher == null) {
                return ApiResultHandler.success("查询成功", teacherService.selectAll(null));
            }
            return ApiResultHandler.success("查询成功",teacherService.selectAll(teacher));
        }catch (Exception e){
            return ApiResultHandler.buildApiResult(500,"查询失败",null);
        }
    }

    @PutMapping("/{id}")
    public ApiResult update(@RequestBody Teacher teacher) {
        int res = teacherService.updateTeacher(teacher);
        if (res == 1){
            return ApiResultHandler.buildApiResult(200,"更新成功",teacher);
        }
        else {
            return ApiResultHandler.buildApiResult(400,"更新失败",null);
        }
    }


    @DeleteMapping("/{id}")
    public ApiResult delete(@PathVariable Integer id) {
        int res = teacherService.deleteTeacher(id);
        if (res == 1){
            return ApiResultHandler.buildApiResult(200, "删除成功", null);
        }else {
            return ApiResultHandler.buildApiResult(400,"删除失败",null);
        }

    }


    @PostMapping
    public ApiResult add(@RequestBody Teacher teacher){
        int res = teacherService.addTeacher(teacher);
        if (res == 1){
            return ApiResultHandler.buildApiResult(200,"添加成功",teacher);
        }
        else {
            return ApiResultHandler.buildApiResult(400,"添加失败",null);
        }
    }
}
