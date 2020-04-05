package com.art.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowByTimeController {


    private final String startTime = "8:00";
    private final String endTime = "9:00";


    // 字符串 转 日期
    public static Date strToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
        }
        return date;
        }


    private void formatTime(String startTime,String endTime,String finalStartTime,String finalEndTime){


    }

}
