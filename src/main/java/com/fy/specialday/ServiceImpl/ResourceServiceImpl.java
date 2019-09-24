package com.fy.specialday.ServiceImpl;

import cn.hutool.core.lang.Snowflake;
import com.fy.specialday.Entity.Resource;
import com.fy.specialday.Entity.SysUser;
import com.fy.specialday.Mapper.ResourceMapper;
import com.fy.specialday.Service.ResourceService;
import com.fy.specialday.Util.FileUpload;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    Snowflake snowflake;

    @Override
    public int deleteByPrimaryKey(BigDecimal id) {
        return resourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Resource record) {
        return resourceMapper.insert(record);
    }

    @Override
    public int insertSelective(Resource record) {
        return resourceMapper.insertSelective(record);
    }

    @Override
    public Resource selectByPrimaryKey(BigDecimal id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Resource record) {
        return resourceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Resource record) {
        return resourceMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Resource> findByAll(Resource resource) {
        return resourceMapper.findByAll(resource);
    }

    public PageInfo<Resource> findByAllwithPage(int page, int pageSize, Resource resource) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(resourceMapper.findByAll(resource));
    }

    @Override
    public List<Resource> uploadResource(List<MultipartFile> files) {
        List<Resource> uploadSuccessResource = new LinkedList<>();
        SysUser principal = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        files.stream()
                .filter(multipartFile -> multipartFile != null && !multipartFile.isEmpty())
                .forEach(multipartFile -> {
                    try {
                        Long id = snowflake.nextId();
                        Path path = FileUpload.uploadFile(multipartFile, id);
                        Path thumbnail = FileUpload.generateThumbnail(multipartFile, id);
                        if (Files.exists(path)) {
                            Resource resource = new Resource();
                            resource.setId(BigDecimal.valueOf(id));
                            resource.setPhotopath(path.toString());
                            resource.setCreatetime(new Date());
                            resource.setUserid(principal.getId());
                            resource.setCreatetime(new Date());
                            if (Files.exists(thumbnail)) {
                                resource.setThumbnailpath(thumbnail.toString());
                            } else {
                                resource.setThumbnailpath(path.toString());
                            }
                            resource.setType(multipartFile.getContentType());
                            resource.setSize(BigDecimal.valueOf(multipartFile.getSize()));

                            //设置预览路径
                            resource.setPreviewPath(FileUpload.PREVIEW_URL + resource.getId());
                            int i = resourceMapper.insertSelective(resource);
                            if (i <= 0) {
                                //插入失败删除文件
                                Files.deleteIfExists(path);
                            } else {
                                uploadSuccessResource.add(resource);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        return uploadSuccessResource;
    }
}

