package com.fy.specialday.ServiceImpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.fy.specialday.Mapper.SysLogMapper;
import com.fy.specialday.Entity.SysLog;
import com.fy.specialday.Service.SysLogService;
@Service
public class SysLogServiceImpl implements SysLogService{

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return sysLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysLog record) {
        return sysLogMapper.insert(record);
    }

    @Override
    public int insertSelective(SysLog record) {
        return sysLogMapper.insertSelective(record);
    }

    @Override
    public SysLog selectByPrimaryKey(String id) {
        return sysLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SysLog record) {
        return sysLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SysLog record) {
        return sysLogMapper.updateByPrimaryKey(record);
    }

}
