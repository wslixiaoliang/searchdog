/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.elastic.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    /**
     *
     * 将查询结果，以流的方式写出文件
     * @param str
     */
    public static void outFile(String str) throws IOException {
        File file;
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try
        {
            file = new File("/Users/wslixiaoliang/Documents/documents/output/outWrite.txt");
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream,"utf-8");
            //若文件目录不存在，创建目录
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            //若文件不存在，创建它
            if(!file.exists()) {
                file.createNewFile();
            }
            outputStreamWriter.append(str);

        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage()+e);
        }finally {

            if(null!=outputStreamWriter)
            {
                outputStreamWriter.close();
            }

            if(null!=fileOutputStream)
            {
                fileOutputStream.close();
            }

        }

    }


    /**s
     * 压缩文件
     * @param srcFilePath 压缩源路径
     * @param destFilePath 压缩目的路径
     */
    public static void compress(String srcFilePath, String destFilePath) {

        File src = new File(srcFilePath);

        if (!src.exists()) {
            throw new RuntimeException(srcFilePath + "不存在");
        }
        File zipFile = new File(destFilePath);

        try {

            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);
            String baseDir = "";
            compressbyType(src, zos, baseDir);
            zos.close();

        } catch (Exception e) {
            System.out.println(e.getMessage()+e);
            e.printStackTrace();

        }
    }

    /**
     * 判断类型：文件/文件夹
     * @param src
     * @param zos
     * @param baseDir
     */
    private static void compressbyType(File src, ZipOutputStream zos,String baseDir) {

        if (!src.exists())
            return;
        System.out.println("压缩路径" + baseDir + src.getName());
        //判断文件是否是文件，如果是文件调用compressFile方法,如果是路径，则调用compressDir方法；
        if (src.isFile()) {
            //src是文件，调用此方法
            compressFile(src, zos, baseDir);

        } else if (src.isDirectory()) {
            //src是文件夹，调用此方法
            compressDir(src, zos, baseDir);

        }

    }

    /**
     * 压缩文件
     */
    private static void compressFile(File file, ZipOutputStream zos,String baseDir) {
        if (!file.exists())
            return;
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(baseDir + file.getName());
            zos.putNextEntry(entry);
            int count;
            byte[] buf = new byte[1024];
            while ((count = bis.read(buf)) != -1) {
                zos.write(buf, 0, count);
            }
            bis.close();

        } catch (Exception e) {
            System.out.println(e.getMessage()+e);
        }
    }

    /**
     * 压缩文件夹
     */
    private static void compressDir(File dir, ZipOutputStream zos,String baseDir) {
        if (!dir.exists())
            return;
        File[] files = dir.listFiles();
        if(files.length == 0){
            try {
                zos.putNextEntry(new ZipEntry(baseDir + dir.getName()+File.separator));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (File file : files) {
            compressbyType(file, zos, baseDir + dir.getName() + File.separator);
        }
    }


    /**
     * 文件解压
     * @param srcPath
     * @param dstPath
     * @param password
     * @return
     * @throws IOException
     */
    public static boolean decrypt(String srcPath,String dstPath,String password) throws IOException{
        try {
            if(!new File(srcPath).exists()) {
                System.out.println("源路径不存在 "+srcPath);
                return false;
            }
            ZipFile srcFile = new ZipFile(srcPath);
            srcFile.setFileNameCharset("GBK");
            srcFile.setPassword(password.toCharArray());
            srcFile.extractAll(dstPath);
            System.out.println("成功解密文件");
            return true;
        } catch (ZipException e) {
            System.out.println("解密文件发生异常:"+e);
            return false;
        }
    }


    /**
     * main 方法测试
     * @param args
     */
    public static void main (String args[]){

        //写入文件
        String str2 = "[\n" +
                "  {\n" +
                "    \"portraitId\": \"9\",\n" +
                "    \"famousId\": \"9\",\n" +
                "    \"exceptAnatation\": \"弗拉基米尔·伊里奇·乌里扬诺夫(列宁)\",\n" +
                "    \"portraitName\": \"liening.png\",\n" +
                "    \"relativeLocation\": \"E:/famous/portrait\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"portraitId\": \"9\",\n" +
                "    \"famousId\": \"9\",\n" +
                "    \"exceptAnatation\": \"弗拉基米尔·伊里奇·乌里扬诺夫(列宁)\",\n" +
                "    \"portraitName\": \"liening.png\",\n" +
                "    \"relativeLocation\": \"E:/famous/portrait\"\n" +
                "  }\n" +
                "]";
        try {
            outFile(str2);
            System.out.println(str2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //压缩文件
        String srcPath = "/Users/wslixiaoliang/Documents/documents/output/outWrite.txt";
        String dstPath = "/Users/wslixiaoliang/Documents/documents/output/outWrite.zip";
        compress(srcPath,dstPath);


        //解压文件
        String srcPath1="/Users/wslixiaoliang/Documents/documents/output/MFT_WHOLE_ACCOUNT_INFO-20190918173212.zip";
        String dstPath1="/Users/wslixiaoliang/Documents/documents/output";
        String password = "iHRsxcg66!";
        try {
            boolean flag1 = decrypt(srcPath1,dstPath1,password);
            System.out.println("============================="+flag1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
