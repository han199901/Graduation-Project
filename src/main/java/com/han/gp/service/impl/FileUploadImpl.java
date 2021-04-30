package com.han.gp.service.impl;

import com.google.gson.Gson;
import com.han.gp.configuration.property.QnConfig;
import com.han.gp.configuration.property.SystemConfig;
import com.han.gp.service.FileUpload;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@Service
public class FileUploadImpl implements FileUpload {
    private final Logger logger = LoggerFactory.getLogger(FileUpload.class);
    private final SystemConfig systemConfig;


    @Autowired
    public FileUploadImpl(SystemConfig systemConfig) {
        this.systemConfig = systemConfig;
    }

    @Override
    public String uploadFile(InputStream inputStream, long size, String extName) {
        QnConfig qnConfig = systemConfig.getQn();
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qnConfig.getAccessKey(), qnConfig.getSecretKey());
        String upToken = auth.uploadToken(qnConfig.getBucket());
        try {
            Response response = uploadManager.put(inputStream, null, upToken, null, null);
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return qnConfig.getUrl() + "/" + putRet.key;
        } catch (QiniuException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public String uploadByteImage(byte[] uploadBytes) {
        QnConfig qnConfig = systemConfig.getQn();
        Configuration cfg = new Configuration(Region.region2());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(qnConfig.getAccessKey(), qnConfig.getSecretKey());
        String upToken = auth.uploadToken(qnConfig.getBucket());
        try {
            Response response = uploadManager.put(uploadBytes, null, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return qnConfig.getUrl() + "/" + putRet.key;
        } catch (QiniuException ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }
}
