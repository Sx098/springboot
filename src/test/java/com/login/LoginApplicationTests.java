package com.login;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.login.entity.Admin;
import com.login.entity.Uer;
import com.login.mapper.AdminMapper;
import com.login.service.impl.UerServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginApplicationTests {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UerServiceImpl uerService;
    @Test
    void contextLoads() {
        System.out.println(adminMapper.selectById(1));
    }
    @Test
    void e(){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("name","admin").eq("password","123456");
        System.out.println(adminMapper.selectOne(wrapper));
    }
    @Test
    void o(){
        Uer uer=new Uer();
        uer.setId(1);
        uer.setName("nihao");
        uer.setAge(22);
        System.out.println(uerService.updateById(uer));;
    }
}
