package com.fy.specialday.Mapper;

import com.fy.specialday.Entity.SysRolePermission;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(@Param("roleid") BigDecimal roleid, @Param("permissionid") BigDecimal permissionid);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}