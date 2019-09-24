package com.fy.specialday.Mapper;

import com.fy.specialday.Entity.Resource;
import java.math.BigDecimal;import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> findByAll(Resource resource);
}