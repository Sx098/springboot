package com.login.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.login.entity.Admin;
import com.login.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IService<Admin> {
    @Resource
    private AdminMapper adminMapper;
    public Admin findById(Integer id){return adminMapper.selectById(id);}

}
