package com.art.web.component;

import com.alibaba.dubbo.common.utils.StringUtils;

public class WslixiaoliangUtil {

    /**
     * 分割字符串，以逗号隔开
     * @param str
     * @return
     */
    public static String[] splitStr(String str){
        int eq = str.indexOf(",");
        if( eq > -1 ){
            return str.split(",");
        }else{
            if(StringUtils.isEmpty(str)) {
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
        if (StringUtils.isEmpty(rangeContent))
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
    public static void appandTobuilder(StringBuilder aBuild,String str){
        if(StringUtils.isEmpty(str)) return;
        if( aBuild.length() > 0 ){
            aBuild.append(",").append(str);
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
        if( StringUtils.isEmpty(str) || StringUtils.isEmpty(character) ) return "";
        int eq = str.lastIndexOf(character);
        if( eq > -1 ){
            return str.substring(0,str.lastIndexOf(character));
        }else{
            return str;
        }
    }

}
