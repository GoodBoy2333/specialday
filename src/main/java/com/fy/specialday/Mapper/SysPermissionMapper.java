package com.fy.specialday.Mapper;

import com.fy.specialday.Entity.SysPermission;
import java.math.BigDecimal;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}