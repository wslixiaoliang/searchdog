/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.elastic.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonUtil {

    /**
      * 判断字符串是否为json字符串
      * @param jsonstr
      * @return
      */
    public static boolean isJson(String str){
        try{

            Object json = new JSONTokener(str).nextValue();
            if(json instanceof JSONObject){
                return true;
            }
            else if(json instanceof JSONArray){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){

            System.out.println(e.getMessage()+e);
            return false;
        }

    }

    /**
     * 解析json对象
     * @param jsonString
     */
    public static String analysisJson(String jsonString) throws JSONException {

        String anantation = "";
        if(StringUtil.isNotEmpty(jsonString) && isJson(jsonString)){
            JSONObject jsonObject = new JSONObject(jsonString);
            if(null!=jsonObject && StringUtil.isNotEmpty(jsonObject.getString("anantation"))){
                anantation = jsonObject.getString("anantation");
            }
        }else{
            return anantation;
        }
        return anantation;
    }

    /**
     * 解析jsonArray
     * @param jsonArrayStr
     * @return
     */
    public static String analysisJsonArray(String jsonArrayStr) throws JSONException{

        String exceptAnatation = "";
        StringBuilder sub = new StringBuilder();
        if(StringUtil.isNotEmpty(jsonArrayStr) && isJson(jsonArrayStr)){

            JSONArray jsonArray = new JSONArray(jsonArrayStr);
            if(null!=jsonArray && jsonArray.length()>0){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if(null!=jsonObject && StringUtil.isNotEmpty(jsonObject.getString("exceptAnatation"))){
                        exceptAnatation = jsonObject.getString("exceptAnatation");
                        if(sub.length() > 0){
                            sub.append(",").append(exceptAnatation);
                        }else{
                            sub.append(exceptAnatation);
                        }
                    }
                }
            }
        }else{
            return exceptAnatation;
        }
        return sub.toString();
    }


    /**
     * main 方法测试
     * @param args
     */
    public static void main (String args[]){

        String str= "{\n" +
                "\"portraitId\": \"2\",\n" +
                "\"famousId\": \"2\",\n" +
                "\"anantation\": \"玛丽·居里\",\n" +
                "\"portraitName\": \"julifuren.png\",\n" +
                "\"relativeLocation\": \"E:/famous/portrait\"\n" +
                "}";

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


        String  anatation = "";
        String exceptAnatation = "";
        boolean flag = isJson(str2);
        try {
            anatation = analysisJson(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            exceptAnatation = analysisJsonArray(str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("=============="+flag);
        System.out.println("=============="+anatation);
        System.out.println("=============="+exceptAnatation);
    }

}


