package com.fy.specialday.Controller;

import cn.hutool.core.io.IoUtil;
import com.fy.specialday.ApiCommon.ApiResponse;
import com.fy.specialday.Common.Status;
import com.fy.specialday.Entity.Resource;
import com.fy.specialday.Service.ResourceService;
import com.fy.specialday.ServiceImpl.ResourceServiceImpl;
import com.fy.specialday.Util.ResponseUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 〈资源控制类〉
 *
 * @author fangyan
 * @create 2019/9/22
 * @since 1.0.0
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    ResourceService resourceService;

    @Autowired
    ResourceServiceImpl resourceServiceImpl;

    @GetMapping
    public ApiResponse<PageInfo<Resource>> findByAll(@RequestParam(required = false, defaultValue = "1") int page,
                                                     @RequestParam(required = false, defaultValue = "10") int pageSize, Resource resource) {
        return ApiResponse.success(resourceServiceImpl.findByAllwithPage(page, pageSize, resource));
    }

    @PostMapping
    public ApiResponse upload(List<MultipartFile> files) {
        try {
            if (files == null || files.size() == 0) {
                return ApiResponse.fail("上传文件列表为空");
            }
            List<Resource> resources = resourceService.uploadResource(files);
            return ApiResponse.success(resources);
        } catch (Exception e) {
            return ApiResponse.fail("上传失败");
        }
    }

    @GetMapping("/photo/{id}")
    public void photo(@PathVariable BigDecimal id, HttpServletResponse response, @RequestParam(defaultValue = "0") int isOriginal) {
        Resource resource = resourceService.selectByPrimaryKey(id);
        if (resource == null) {
            ResponseUtil.renderJson(response, Status.ERROR);
            return;
        }
        String photopath = resource.getPhotopath();
        String thumbnailpath = resource.getThumbnailpath();
        if (StringUtils.isEmpty(photopath)) {
            ResponseUtil.renderJson(response, Status.ERROR);
            return;
        }
        //默认缩略图展示
        Path path = Paths.get(thumbnailpath);
        if (isOriginal != 0) {
            path = Paths.get(photopath);
        }
        if (Files.notExists(path)) {
            ResponseUtil.renderJson(response, Status.ERROR);
            return;
        }
        response.setContentType("image/jpeg");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            InputStream inputStream = Files.newInputStream(path);
//            第一种做法
//            int count = 0;
//            byte[] buffer = new byte[1024];
//            while ((count = inputStream.read(buffer)) != -1)
//                outputStream.write(buffer, 0, count);
//            outputStream.flush();
//            第二种做法
//            byte[] buffer = new byte[1024];
//            int read;
//            try {
//                while ((read = inputStream.read(buffer)) != -1) {
//                    if (read > 0) {
//                        byte[] chunk;
//                        if (read == 1024) {
//                            chunk = buffer;
//                        } else {
//                            chunk = new byte[read];
//                            System.arraycopy(buffer, 0, chunk, 0, read);
//                        }
//                        outputStream.write(chunk);
//                        outputStream.flush();
//                    }
//                }
//            } finally {
//                inputStream.close();
//            }
//            第三种做法
            byte[] bytes = IoUtil.readBytes(inputStream);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
