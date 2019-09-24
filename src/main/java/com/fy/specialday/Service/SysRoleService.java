package com.fy.specialday.Service;
import java.util.List;

import java.math.BigDecimal;
import com.fy.specialday.Entity.SysRole;
public interface SysRoleService{


    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);



	List<SysRole> findByAll(SysRole sysRole);


}
