package com.art.web.controller;

import com.art.beans.famous.FamousPortrait;
import com.art.beans.famous.Result;
import com.art.util.StringUtil;
import com.art.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class FamousControllerTest {

    @Autowired
    private FamousController collegeController;

    @Test
    public void test(){
        FamousPortrait famous = new FamousPortrait();
        famous.setChineseName("屠呦呦");
        Integer page = 1;
        Integer limit = 50;
//        Result result = collegeController.getWorldFamous(page,limit);
        System.out.println("=====================查询结果如下==============");
//        List<FamousPortrait> list = result.getBeans();
//        if(result!=null){
//            for(FamousPortrait famous1:list){
//                System.out.println(famous1.getFamousId());
//                System.out.println(famous1.getSex());
//                System.out.println(famous1.getChineseName());
//                System.out.println(famous1.getEnglishName());
//                System.out.println(famous1.getCareer());
//                System.out.println(famous1.getHonor());
//                System.out.println(famous1.getAchievement());
//                System.out.println(famous1.getCountry());
//                System.out.println(famous1.getBirthYear());
//                System.out.println("=========================");
////            }
//        }
    }


    /**
     * 根据返回的flag 控制app按钮的显隐
     * flag:true(按钮展示)，flag:false(按钮隐藏)
     */
    public boolean controlButtonShow(){

        Map<String,Object> timeparams;
        String startTime = "";
        String endTime = "";
        String nowTime = date2String(new Date(),"yyyy-MM-dd HH:mm:ss");
        String yearMothDay = date2String(new Date(),"yyyy-MM-dd");
        String HourMinutesSeconds = date2String(new Date(),"HH:mm:ss");
        int hour = Integer.valueOf(getHours(HourMinutesSeconds));

        timeparams = getTime(hour);
        if(!timeparams.isEmpty() && timeparams.containsKey("startTime")){
            startTime = String.valueOf(timeparams.get("startTime"));
        }
        if(!timeparams.isEmpty() && timeparams.containsKey("endTime")){
            endTime = String.valueOf(timeparams.get("endTime"));
        }

        String fullStartTime = appendTime(startTime,yearMothDay);//拼接完整的开始时间
        String fullEndTime = appendTime(endTime,yearMothDay);//拼接完整的结束时间

        boolean flag = checkCalendar(string2Date(nowTime),string2Date(fullStartTime),string2Date(fullEndTime));
        return flag;
    }

    /**
     * 获取 开始时间 && 结束时间
     * time<11：00 上午状态：am, 11<=time<=14 中午状态：middle, time>14:00 下午状态：pm
     * @param hour
     * @return
     */
    private Map<String,Object> getTime(int hour){

        Map<String,Object> params = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        String startTime = "";
        String endTime = "";
        //00:00-11:00
        if(hour<11){
            params.put("status","am");
            //调用后台接口获取 开始 结束时间
            startTime = "";
            endTime = "";

            result.put("startTime",startTime);
            result.put("endTime",endTime);
        }
        //11:00-14:00
        if(hour>=11 && hour<=14){
            params.put("status","middle");
            //调用后台接口获取 开始 结束时间
            startTime = "";
            endTime = "";

            result.put("startTime",startTime);
            result.put("endTime",endTime);
        }
        //14:00-24:00
        if(hour>14){
            params.put("status","pm");
            //调用后台接口获取 开始 结束时间
            startTime = "";
            endTime = "";

            result.put("startTime",startTime);
            result.put("endTime",endTime);
        }
        return result;
    }



    /**
     * 组装完整时间： yyyy-MM-dd HH:mm:ss
     * @param time
     * @param yearMothDay
     * @return
     */
    private String appendTime(String time, String yearMothDay){

        StringBuilder sub = new StringBuilder();
        if(StringUtil.isNotEmpty(time) && StringUtil.isNotEmpty(yearMothDay)){
            sub.append(yearMothDay).append(" ").append(time);
        }
        return sub.toString();
    }

    /**
     * 获取小时
     * @param HourMinutesSeconds
     */
    private String getHours(String HourMinutesSeconds){

        final String patern = ":";
        if(StringUtil.isNotEmpty(HourMinutesSeconds)){
            String[] splitTime = StringUtil.splitStr(HourMinutesSeconds,patern);
            HourMinutesSeconds = splitTime[0];
        }
        return HourMinutesSeconds;
    }

    /**
     * Date类型 转化成 String类型
     * @param date
     * @param pattern
     * @return
     */
    public static String date2String(Date date, String pattern)
    {
        if(null==date || pattern==null){
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * String类型 转化成 Date类型
     * @param str
     * @return
     */
    public static Date string2Date(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {

        }
        return date;
    }

    /**
      * 判断时间是否在时间段内
      * Date.after(begin)，当Date1大于begin时，返回TRUE；
      * Date.before(end)，当Date1小于end时，返回TRUE；
      * @param nowTime
      * @param beginTime
      * @param endTime
      * @return
      */
    public static boolean checkCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
        return false;
        }
    }

    /**
     * 测试
     *
     */
    @Test
    public void test1(){


        final String start1 = "08:00:00";
//        final String start2 = "11:45:00";
//        final String start3 = "17:30:00";
//
        final String end1 = "01:00:00";
//        final String end1 = "09:00:00";
//        final String end2 = "14:00:00";
//        final String end3 = "18:30:00";
//
//        boolean flag = controlButtonShow();
//        System.out.println("==================="+flag);

        int hour = Integer.valueOf(getHours(end1));

        System.out.println("=================="+hour);


//        String fullTime = date2String(new Date(),"yyyy-MM-dd HH:mm:ss");
//        String hms = date2String(new Date(),"HH:mm:ss");
//        String ymd = date2String(new Date(),"yyyy-MM-dd");
//
//        System.out.println("============"+hms);
//        System.out.println("============"+ymd);
//        System.out.println("============"+fullTime);
    }



}

