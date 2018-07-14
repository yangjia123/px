package com.pixivx.www.Helper;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class AliyunOssHelper {
    public static String upload(File file){
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAImCDieKDLfLYm";
        String accessKeySecret = "w1JEHNIR8gL1LpLfkVeZRVrcwEj2R0";
        String bucketName = "pixivx";
        String fileHost = "work";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dataStr = format.format(new Date());

        if(file==null){
            return null;
        }
        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        String fileUrl = fileHost+"/"+(dataStr+UUID.randomUUID().toString().replace("-","")+"-"+file.getName());
        PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName,fileUrl,file));

        ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);
        if(null!=result){
            return fileUrl;
        }
        ossClient.shutdown();
        return null;
    }
}
