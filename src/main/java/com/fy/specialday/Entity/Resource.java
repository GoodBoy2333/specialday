package com.fy.specialday.Entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Resource {
    /**
     * id
     */
    private BigDecimal id;

    /**
     * 路径
     */
    private String photopath;

    /**
     * 上传时间
     */
    private Date createtime;

    /**
     * 用户id
     */
    private BigDecimal userid;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 缩略图路径
     */
    private String thumbnailpath;

    /**
     * 类别
     */
    private String type;

    /**
     * 说明字段
     */
    private String word;

    /**
     * 搜索key
     */
    private String key;

    /**
     * 大小
     */
    private BigDecimal size;


    public String previewPath;
}