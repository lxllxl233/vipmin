package com.vipmin.controller;

import com.aliyun.oss.OSS;
import com.vipmin.resp.ResponseUtil;
import com.vipmin.util.CodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Api(tags = "一些工具")
@RestController
public class UtilController {

    @Autowired
    private OSS ossClient;
    //读取配置文件
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;

    @ApiOperation("上传文件/图片 oss")
    @PostMapping("/api/util/uploadFile")
    public ResponseUtil uploadFile(MultipartFile multipartFile){
        try{
            //时间错标示文件防止文件重复
            long time = new Date().getTime();
            try {
                ossClient.putObject(bucketname, "avatar/"+ time + multipartFile.getOriginalFilename(), multipartFile.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return com.vipmin.resp.ResponseUtil.ok("上传成功").data("url","https://"+bucketname+"."+endpoint+"/avatar/"+time+multipartFile.getOriginalFilename());
        }catch(Exception e){
            e.printStackTrace();
            return ResponseUtil.error("上传失败").data("url",null);
        }
    }

    @ApiOperation("获取二维码，返回 base64 串")
    @GetMapping("/api/util/getCode")
    public ResponseUtil getCode(String url,int high,int width){
        try{
            return ResponseUtil.ok("获取成功").data("img", CodeUtil.getQRCodeBase64(url,width,high));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseUtil.error("获取失败").data("img",null);
        }
    }
}
