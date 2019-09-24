package com.fy.specialday.ServiceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import com.fy.specialday.Entity.SysRolePermission;
import com.fy.specialday.Mapper.SysRolePermissionMapper;
import com.fy.specialday.Service.SysRolePermissionService;
@Service
public class SysRolePermissionServiceImpl implements SysRolePermissionService{

    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public int deleteByPrimaryKey(BigDecimal roleid,BigDecimal permissionid) {
        return sysRolePermissionMapper.deleteByPrimaryKey(roleid,permissionid);
    }

    @Override
    public int insert(SysRolePermission record) {
        return sysRolePermissionMapper.insert(record);
    }

    @Override
    public int insertSelective(SysRolePermission record) {
        return sysRolePermissionMapper.insertSelective(record);
    }

}
