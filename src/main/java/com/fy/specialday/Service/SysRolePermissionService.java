package com.fy.specialday.Service;

import java.math.BigDecimal;
import com.fy.specialday.Entity.SysRolePermission;
public interface SysRolePermissionService{


    int deleteByPrimaryKey(BigDecimal roleid,BigDecimal permissionid);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

}
