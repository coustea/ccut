package com.example.service;

import com.example.entity.Admin;
import com.example.entity.User;
import com.example.exception.CustomerException;
import com.example.mapper.AdminMapper;
import com.example.utils.TakenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    //管理员登陆
    public Admin login(User user) {
        Admin dbAdmin = adminMapper.selectByUsername(user.getUsername());
        if(dbAdmin == null){
            throw new CustomerException("账号不存在！");
        }

        if(!dbAdmin.getPassword().equals(user.getPassword())){
            throw new CustomerException("账号或密码错误");
        }

        String token = TakenUtils.createToken(dbAdmin.getId() + "-" + "STUDENT",dbAdmin.getPassword());
        dbAdmin.setToken(token);
        return dbAdmin;
    }
    //添加管理员
    public int addAdmin(Admin admin) {

        return adminMapper.insert(admin);
    }
    //删除管理员
    public int deleteAdmin(Integer id) {

        return adminMapper.deleteById(id);
    }
    //修改学生
    public int updateAdmin(Admin admin) {
        return adminMapper.updateById(admin);

    }
    //分页查询
    public PageInfo<Admin> selectPage(Integer pageNum, Integer pageSize, Admin admin) {
        PageHelper.startPage(pageNum,pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }
}
