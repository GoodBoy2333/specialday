package com.fy.specialday.ServiceImpl;
import com.fy.specialday.Entity.SysUser;
import com.fy.specialday.Mapper.SysUserMapper;
import com.fy.specialday.Service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
@Service
public class SysUserServiceImpl implements SysUserService{

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysUser record) {
        return sysUserMapper.insert(record);
    }

    @Override
    public int insertSelective(SysUser record) {
        return sysUserMapper.insertSelective(record);
    }

    @Override
    public SysUser selectByPrimaryKey(BigDecimal id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser record) {
        return sysUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysUser record) {
        return sysUserMapper.updateByPrimaryKey(record);
    }

	@Override
	public SysUser findOneByUsernameOrEmailAndPassword(String username,String email,String password){
		 return sysUserMapper.findOneByUsernameOrEmailAndPassword(username,email,password);
	}

	@Override
	public List<SysUser> findByUsernameOrEmail(String username,String email){
		 return sysUserMapper.findByUsernameOrEmail(username,email);
	}







}
