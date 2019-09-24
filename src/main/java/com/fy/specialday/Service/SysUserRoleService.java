package com.fy.specialday.Service;

import java.math.BigDecimal;
import com.fy.specialday.Entity.SysUserRole;
public interface SysUserRoleService{


    int deleteByPrimaryKey(BigDecimal userid,BigDecimal roleid);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

}
