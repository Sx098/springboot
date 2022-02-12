package com.login.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.login.common.Result;
import com.login.entity.Uer;
import com.login.service.impl.UerServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UerController {
    @Resource
    private UerServiceImpl uerService;

    @PostMapping
    public Result add(@RequestBody Uer uer){
        uerService.save(uer);
        return Result.success();
    }
    @PutMapping
    public Result updata(@RequestBody Uer uer){
        uerService.updateById(uer);
        return Result.success();
    }
    // 删除用户
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        uerService.delete(id);
    }
    @GetMapping("/{id}")
    public Result<Uer> findById(@PathVariable Long id){ return Result.success(uerService.findById(id));}
    @GetMapping
    public Result<List<Uer>> findAll(){
        return Result.success(uerService.findAll());
    }
    @GetMapping("/page")
    public Result<Page<Uer>> findPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name){
        return Result.success(uerService.findPage(pageNum, pageSize,name));
    }
}
