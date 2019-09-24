package com.fy.specialday.Entity;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class SysPermission {
    /**
    * 主键
    */
    private BigDecimal id;

    /**
    * 权限名
    */
    private String name;

    /**
    * 类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址
    */
    private String url;

    /**
    * 权限类型，页面-1，按钮-2
    */
    private String type;

    /**
    * 权限表达式
    */
    private String permission;

    /**
    * 后端接口访问方式
    */
    private String method;

    /**
    * 排序
    */
    private Long sort;

    /**
    * 父级id
    */
    private BigDecimal parentid;
}