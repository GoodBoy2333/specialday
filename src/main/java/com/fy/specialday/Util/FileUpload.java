package com.fy.specialday.Util;

import cn.hutool.core.io.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

/**
 * 〈文件上传〉
 *
 * @author fangyan
 * @create 2019/9/22
 * @since 1.0.0
 */
public class FileUpload {

    public static String PREVIEW_URL = "http://fy770.oicp.net/resource/photo/";

    public static Path uploadFile(MultipartFile file, Long id) throws IOException {
        Path path = getPath();
//        Path resolve = path.resolve(file.getOriginalFilename());
        String fileName = id + "." + FileUtil.extName(file.getOriginalFilename());
        Path resolve = path.resolve(fileName);
        if (Files.exists(resolve)) {
            Files.deleteIfExists(resolve);
        }
        file.transferTo(resolve);
        return resolve;
    }

    /**
     * 生成缩略图
     *
     * @param file
     * @param id
     * @return
     * @throws IOException
     */
    public static Path generateThumbnail(MultipartFile file, Long id) throws IOException {
        Path path = getPath();
        BufferedImage image = ImageIO.read(file.getInputStream());

        int destWidth = 200;
        int destHeight = 200;
        String fileName = id + "_" + destWidth + "_" + destHeight + "." + FileUtil.extName(file.getOriginalFilename());
        Path resolve = path.resolve(fileName);
        boolean confirm = confirmSize(image, destWidth, destHeight);
        if (confirm) {
            if (Files.exists(resolve)) {
                Files.deleteIfExists(resolve);
            }
            // 按比例缩小至目标尺寸
            Thumbnails.Builder<BufferedImage> builder = Thumbnails.of(image);
            builder.crop(Positions.CENTER).size(destWidth, destHeight);
            // 保存
            builder.toFile(resolve.toAbsolutePath().toString());
        }
        return resolve;
    }

    /**
     * 确保原图够大
     *
     * @param image
     * @param destWidth
     * @param destHeight
     * @return
     * @throws IOException
     */
    public static boolean confirmSize(BufferedImage image, int destWidth, int destHeight) throws IOException {
        if (image == null || image.getWidth() < destWidth || image.getHeight() < destHeight) {
            return false;
        }
        return true;
    }

    public static Path getPath() throws IOException {
        LocalDate now = LocalDate.now();
        Path path = Paths.get(now.getYear() + "/" + now.getMonthValue() + "/" + now.getDayOfMonth());
        if (Files.notExists(path)) {
            Files.createDirectories(path);
        }
        return path;
    }
}
