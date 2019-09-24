package com.fy.specialday.Service;

import com.fy.specialday.Entity.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface ResourceService {


    int deleteByPrimaryKey(BigDecimal id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> findByAll(Resource resource);

    List<Resource> uploadResource(List<MultipartFile> files);
}

