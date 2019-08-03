package com.art.util.famous;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class LiangUtil {


    /**
     * 判断对象不为空
     * @param param
     */
    public static boolean isNotEmpty(String param){

        if(null!=param && ""!=param && "null"!=param && !"".equals(param.trim())&& !"null".equals(param.trim())){
            return true;
        }
        return false;

    }

    /**
     * 判断对象为空
     * @param param
     * @return
     */
    public static boolean isEmpty(String param){

        return !isNotEmpty(param);

    }

    /**
     * 分割字符串，以制定分隔符隔开
     * @param str
     * @return
     */
    public static String[] splitStr(String str,String pattern){
        int eq = str.indexOf(pattern);
        if( eq > -1 ){
            return str.split(pattern);
        }else{
            if(isNotEmpty(str)) {
                return null;
            }else {
                return new String[]{str};
            }
        }
    }


    /**
     * 判断是否为数字
     * @param rangeContent
     * @return
     */
    public static boolean _isDigital(String rangeContent) {
        if (isEmpty(rangeContent))
            return false;
        int aDianHaoC = 0;
        for (int index = 0; index < rangeContent.length(); index++) {
            switch (rangeContent.charAt(index)) //String.charAt(index):字符串中,此索引位置的字符
            {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                case '-': {
                    if (index != 0)
                        return false;//一旦return ，整个方法结束
                }
                break;
                case '.': {
                    aDianHaoC++;
                    if (aDianHaoC > 1)
                        return false;
                }
                break;
                default:
                    return false;
            }
        }
        return true;
    }

    /**
     * 按照指定的分割符，将字符串str拼接到StringBuilder
     * @param aBuild
     */
    public static void appandTobuilder(StringBuilder aBuild,String str,String pattern){
        if(isEmpty(str)) return;
        if( aBuild.length() > 0 ){
            aBuild.append(pattern).append(str);
        }else{
            aBuild.append(str);
        }
    }


    /**
     * 以分割符切割字符串（从最右向左查找）
     * @param str  待处理字符串
     * @param character  切割符号
     * @return
     * indexOf 从左往右查，lastIndexOf 从右往左查
     */
    public static String trimRight(String str,String character){
        if( isEmpty(str) || isEmpty(character) ) return "";
        int eq = str.lastIndexOf(character);
        if( eq > -1 ){
            return str.substring(0,str.lastIndexOf(character));
        }else{
            return str;
        }
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


    /**
     * 主键策略时间格式化
     * @param sub
     * @param times
     */
    public static void appendTime(StringBuilder sub,String[] times)
    {
        if(times.length > 0)
        {
            for(int i=0; i< times.length; i++ )
            {
                sub.append(times[i]);
            }
        }
    }


    /**
     * 主键生成策略（long最长可容纳19位）
     * @param nowTime
     * @return
     */
    public static long getPrimaryKey(String nowTime)
    {
        long finalPrimaryKey = 0L;
        if(isEmpty(nowTime) ||nowTime.indexOf(" ")< -1)
        {
            return finalPrimaryKey;
        }

        String [] all = nowTime.split(" ");
        String yearMonthDay = all[0];
        String hourMiniteSecond = all[1];

        String[] yearMonthDays = splitStr(yearMonthDay,"-");
        String[] hourMiniteSeconds = splitStr(hourMiniteSecond,":");

        StringBuilder yearBuilder = new StringBuilder();
        StringBuilder monthBuilder  = new StringBuilder();

        appendTime(yearBuilder,yearMonthDays);
        appendTime(monthBuilder,hourMiniteSeconds);

        //获取5为随机数，因为long类型最长为19位；
        Random r =new Random();
        int random = r.nextInt(100000);
        String sixRandom = String.valueOf(random);
        monthBuilder.append(sixRandom);//将6为随机数拼接在时分秒之后

        String primaryId = yearBuilder.append(monthBuilder).toString();
        long primaryKey = Long.valueOf(primaryId);

        return primaryKey;
    }

}
