package com.art.util;

public class StringUtil {


    /**
     * 判断字符串为空
     * @param param
     * @return
     */
    public static boolean isEmpty(String param){

        return !isNotEmpty(param);

    }

    /**
     * 判断字符串不为空
     * @param param
     */
    public static boolean isNotEmpty(String param){

        if(null!=param && ""!=param && "null"!=param && !"".equals(param.trim())&& !"null".equals(param.trim())){
            return true;
        }
        return false;

    }

    /**
     * 分割字符串（以特定分隔符）
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
     * 按照特定的符号，将字符串str拼接到StringBuilder
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
}
