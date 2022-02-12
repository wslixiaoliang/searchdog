/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.elastic.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@RestController
@RequestMapping(value = "/portrait")
public class FileDownloadController {

    private static final String FAMOUS_PATH  = "/Users/wslixiaoliang/Documents/project/portrait/famous";
    private static final String CONSUMER_PATH = "/Users/wslixiaoliang/Documents/project/portrait/consumer";
    private static final String MESSAGE_SUCCESS="上传成功";
    private static final String MESSAGE_FAILURE="上传失败";
    /**
     * 肖像上传
     * @param file
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile file){


        try{
            if(file.isEmpty()){
                return "上传文件为空";
            }
            String fileName = file.getOriginalFilename();
            logger.info("上传肖像名称为：{}"+fileName);
            // 获取文件的后缀名abc.png
            String suffixName = fileName.substring(fileName.lastIndexOf("."));//包含"."
            logger.info("文件的后缀名为：{}"+suffixName);
            //设置文件的存储路径
            String filedPath = "E:\\famous\\portrait";
            String path = filedPath + suffixName;
            File portrait = new File(path);
            //判断文件的父级目录是否存在，不存在则创建（mkdirs可创建多级父目录，mkdir只能创建一级父目录）
            if(!portrait.getParentFile().exists()){
                portrait.getParentFile().mkdirs();
            }
            file.transferTo(portrait);//写入文件
            return MESSAGE_SUCCESS;
        }catch (IllegalStateException e) {
            logger.error("上传失败:{}",e.getMessage());
        }catch(IOException e){
            logger.error(e.getMessage());
        }
        return MESSAGE_FAILURE;
    }

    /**
     * 肖像下载(新建流：从内到外；关闭流时：从外到内)
     * @param response
     * @param fileName
     */
    @RequestMapping("/downLoad")
    public void downLoad(HttpServletResponse response, String fileName){
        if (fileName != null) {
            //设置文件路径
//            String realPath = "E:/famous/portrait";
            //设置文件路径
            String realPath = FAMOUS_PATH;

            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);//返回一次读取多少行
                    while (i != -1) {
                        os.write(buffer, 0, i);//从0写到i,一次写1024个字节;
                        i = bis.read(buffer);
                    }
                    logger.info("肖像名称为:{}"+fileName+"下载成功");
                } catch (Exception e) {
                    logger.error("肖像下载失败:{}",e.getMessage());
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage());
                        }
                    }
                }
            }
        }
    }


    /**
     * 用户头像下载(新建流：从内到外；关闭流时：从外到内)
     * @param response
     * @param fileName
     */
    @RequestMapping("/consumerDownLoad")
    public void consumerDownLoad(HttpServletResponse response, String fileName){
        if (fileName != null) {

            //设置头像路径
            String realPath = CONSUMER_PATH;

            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);//返回一次读取多少行
                    while (i != -1) {
                        os.write(buffer, 0, i);//从0写到i,一次写1024个字节;
                        i = bis.read(buffer);
                    }
                    logger.info("用户头像名称为:{}"+fileName+"下载成功");
                } catch (Exception e) {
                    logger.error("用户头像下载失败:{}",e.getMessage());
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage());
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            logger.error(e.getMessage());
                        }
                    }
                }
            }
        }
    }





}
