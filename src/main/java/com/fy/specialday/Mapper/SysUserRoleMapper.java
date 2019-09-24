package com.fy.specialday.Mapper;

import com.fy.specialday.Entity.SysUserRole;
import java.math.BigDecimal;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(@Param("userid") BigDecimal userid, @Param("roleid") BigDecimal roleid);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}