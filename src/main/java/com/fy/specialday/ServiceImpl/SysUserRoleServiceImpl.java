package com.fy.specialday.ServiceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.fy.specialday.Mapper.SysUserRoleMapper;
import java.math.BigDecimal;
import com.fy.specialday.Entity.SysUserRole;
import com.fy.specialday.Service.SysUserRoleService;
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService{

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int deleteByPrimaryKey(BigDecimal userid,BigDecimal roleid) {
        return sysUserRoleMapper.deleteByPrimaryKey(userid,roleid);
    }

    @Override
    public int insert(SysUserRole record) {
        return sysUserRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(SysUserRole record) {
        return sysUserRoleMapper.insertSelective(record);
    }

}
