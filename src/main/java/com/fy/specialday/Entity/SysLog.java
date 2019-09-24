package com.fy.specialday.Entity;

import lombok.Data;

@Data
public class SysLog {
    /**
    * ID
    */
    private String id;

    /**
    * 请求IP
    */
    private String ip;

    /**
    * 请求路径
    */
    private String url;

    /**
    * 请求方式
    */
    private String method;

    /**
    * 方法描述
    */
    private String description;

    /**
    * 执行时间
    */
    private String excutetime;

    /**
    * 返回值
    */
    private String returndata;

    /**
    * 用户id
    */
    private String userid;

    /**
    * 用户名
    */
    private String username;
}