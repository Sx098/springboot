package com.login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.login.common.Result;
import com.login.entity.Admin;
import com.login.entity.Uer;
import com.login.service.impl.AdminServiceImpl;
import com.login.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@CrossOrigin
@RestController
public class AdminController {
    @Resource
    AdminServiceImpl adminService;
//    private final String name="admin";
//    private final String password="123456";
    @GetMapping("/log")
    public Admin login(Admin admin, HttpSession session){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("name",admin.getName()).eq("password",admin.getPassword());
        if (adminService.getOne(wrapper)!=null){
            admin.setToken(JwtUtil.createToken());
            session.setAttribute("username",admin.getName());
            return admin;
        }
//        if (name.equals(admin.getName()) && password.equals(admin.getPassword())){
//            admin.setToken(JwtUtil.createToken());
//            return admin;
//        }

        return null;
    }
    @GetMapping("/checkToken")
    public Boolean checkToken(Admin admin){
        System.out.println(admin);
        String token = admin.getToken();
        return JwtUtil.checkToken(token);
    }
    @PostMapping("/log")
    public Result add(@RequestBody Admin admin){
        adminService.save(admin);
        return Result.success();
    }
    @GetMapping("/getUsername")
    @ResponseBody
    public String getUsername(HttpSession httpSession){
        return (String) httpSession.getAttribute("username");
    }
}
