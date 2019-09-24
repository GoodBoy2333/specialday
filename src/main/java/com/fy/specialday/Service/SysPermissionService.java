package com.fy.specialday.Service;

import java.math.BigDecimal;
import com.fy.specialday.Entity.SysPermission;
public interface SysPermissionService{


    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

}
