package com.fy.specialday.Entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class SysRole {
    /**
    * 主键
    */
    private BigDecimal id;

    /**
    * 角色名
    */
    private String name;

    /**
    * 描述
    */
    private String description;

    /**
    * 创建时间
    */
    private Date createtime;

    /**
    * 更新时间
    */
    private Date updatetime;
}