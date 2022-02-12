package com.login.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Uer {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
}
