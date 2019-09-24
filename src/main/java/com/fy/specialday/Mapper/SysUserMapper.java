package com.fy.specialday.Mapper;
import com.fy.specialday.Entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findOneByUsernameOrEmailAndPassword(@Param("username")String username,@Param("email")String email,@Param("password")String password);

    List<SysUser> findByUsernameOrEmail(@Param("username")String username,@Param("email")String email);

}