package com.login.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.login.entity.Uer;
import com.login.mapper.UerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UerServiceImpl extends ServiceImpl<UerMapper, Uer> implements IService<Uer> {
    @Resource
    private UerMapper uerMapper;
    public void delete(Long id) {
        uerMapper.deleteById(id);
    }
    public Uer findById(Long id){return uerMapper.selectById(id);}

    public List<Uer> findAll() {
        return uerMapper.selectList(null);
    }
    public Page<Uer> findPage(Integer pageNum, Integer pageSize, String name)
    {
        Page<Uer> page = new Page<>(pageNum,pageSize);
        return uerMapper.selectPage(page,null);
    }
}
