/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtil {

    /**
     * 获取String类型的当前时间
     * @param dateFormat（格式化样式：yyyyMMdd yyyyMMddHHmmss yyyyMMddHHmmssSSS 皆可 ）
     * @return
     */
    public static String getCurrentTime(String dateFormat)
    {
        Date date  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * Date类型 转化成 String类型
     * @param date
     * @param pattern
     * @return
     */
    public static String date2String(Date date,String pattern)
    {
        if(null==date || pattern==null){
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * String类型 转化成 Date类型
     * @param date
     * @return
     */
    public static Date string2Date(String date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SearchConstans.YYYY_MM_DD_HH_MM_SS);
        try{

            return simpleDateFormat.parse(date);

        }catch (ParseException e){

            return null;
        }

    }

    public static void main (String[] args){

        //String类型时间格式
        String dataFormat = SearchConstans.YYYYMMDD;
        String dataFormat1 = SearchConstans.YYYYMMDDHHMMSS;
        String dataFormat2 = SearchConstans.YYYYMMDDHHMMSSSSS;

        //标准类型时间格式
        String dataFormat3 = SearchConstans.YYYY_MM_DD;
        String dataFormat4 = SearchConstans.YYYY_MM_DD_HH_MM_SS;
        String dataFormat5 = SearchConstans.YYYY_MM_DD_HH_MM_SS_SSS;



        String finalTime = getCurrentTime(dataFormat);
        System.out.println("=============================================="+finalTime);

        String finalTime1 = getCurrentTime(dataFormat1);
        System.out.println("=============================================="+finalTime1);

        String finalTime2 = getCurrentTime(dataFormat2);
        System.out.println("=============================================="+finalTime2);


        String finalTime3 = getCurrentTime(dataFormat3);
        System.out.println("=============================================="+finalTime3);

        String finalTime4 = getCurrentTime(dataFormat4);
        System.out.println("=============================================="+finalTime4);

        String finalTime5 = getCurrentTime(dataFormat5);
        System.out.println("=============================================="+finalTime5);

    }
}
