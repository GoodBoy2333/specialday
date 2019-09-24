package com.fy.specialday.Service;
import com.fy.specialday.Entity.SysUser;

import java.math.BigDecimal;
import java.util.List;
public interface SysUserService{


    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);



	SysUser findOneByUsernameOrEmailAndPassword(String username,String email,String password);



	List<SysUser> findByUsernameOrEmail(String username,String email);



}
