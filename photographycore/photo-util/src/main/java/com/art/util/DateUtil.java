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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constans.YYYY_MM_DD_HH_MM_SS);
        try{

            return simpleDateFormat.parse(date);

        }catch (ParseException e){

            return null;
        }

    }
}
