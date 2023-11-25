package com.lyc.jujiao.manager;


import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.lyc.jujiao.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * Minio 对象存储操作
 */
@Component
public class MinioManager {

    @Resource
    private MinioConfig minioConfig;

    @Resource
    private MinioClient minioClient;

    //存储普通文件
    @Value("${minio.bucket.files}")
    private String bucket_mediafiles;

    /**
     * 上传文件对象到minio
     */
    public void upload(String absolutePath, File file, String filename) {
        try {
            //先得到扩展名
            String extension = filename.substring(filename.lastIndexOf("."));
            //得到mimeType
            String mimeType = getMimeType(extension);
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket(bucket_mediafiles)
                    .object(filename)//添加子目录
                    .filename(absolutePath)
                    .contentType(mimeType)//默认根据扩展名确定文件内容类型，也可以指定
                    .build();
            minioClient.uploadObject(uploadObjectArgs);
            System.out.println("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败");
        }

    }

    //根据扩展名获取mimeType  抽取成一个方法
    private String getMimeType(String extension) {
        //因为是调用ContentInfoUtil.findExtensionMatch(extension)该方法去获取的，如果extension为空，会报空指针
        if (extension == null) {
            extension = "";
        }
        //根据扩展名得到媒体资源类型 mimeType
        //根据扩展名取出mimeType
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(extension);
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;//通用mimeType，字节流 默认是未知的mimeType
        if (extensionMatch != null) {
            mimeType = extensionMatch.getMimeType();
        }
        //如果为空的话，返回未知类型
        return mimeType;
    }

}
