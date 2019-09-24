package com.fy.specialday.ServiceImpl;
import com.fy.specialday.Entity.SysRole;
import com.fy.specialday.Mapper.SysRoleMapper;
import com.fy.specialday.Service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
@Service
public class SysRoleServiceImpl implements SysRoleService{

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysRole record) {
        return sysRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(SysRole record) {
        return sysRoleMapper.insertSelective(record);
    }

    @Override
    public SysRole selectByPrimaryKey(BigDecimal id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysRole record) {
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysRole record) {
        return sysRoleMapper.updateByPrimaryKey(record);
    }

	@Override
	public List<SysRole> findByAll(SysRole sysRole){
		 return sysRoleMapper.findByAll(sysRole);
	}


    public PageInfo<SysRole> findByAllwithPage(int page, int pageSize, SysRole sysRole) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(sysRoleMapper.findByAll(sysRole));
    }
}
