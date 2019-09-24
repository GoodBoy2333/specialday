package com.fy.specialday.Mapper;
import com.fy.specialday.Entity.SysRole;

import java.math.BigDecimal;
import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> findByAll(SysRole sysRole);


}