/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

        /**
         * 主键生成策略,获取19位主键Id（long最长可容纳19位）
         * @param
         * @return
         */
        public static long getPrimaryKey()
        {
            //获取当前17位时间戳
            Date date  = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String time = sdf.format(date);

            //获取2为随机数
            Random r =new Random();
            StringBuilder sub = new StringBuilder(time);
            int random = r.nextInt(100);
            String twoRandom = String.valueOf(random);
            sub.append(twoRandom);//将2为随机数拼接在17位时间之后

            //获取19位long型ID
            long primaryKey = Long.valueOf(sub.toString());
            return primaryKey;
        }

    /**
     * 组装：必返回字段，忽略字段
     * @return
     */
    public static String[] includesOrExcludes(String fieldString){

        String[] fields  = new String[10];
        if(StringUtil.isNotEmpty(fieldString)){
            fields = StringUtil.splitStr(fieldString,",");
        }
        return fields;
    }

    /**
     * main方法测试类
     * @param args
     */
        public static void main(String []args) {
            long primaryKey = getPrimaryKey();
            System.out.println("================="+primaryKey);

        }
    }


