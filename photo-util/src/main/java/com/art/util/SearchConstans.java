/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.util;

/**
 * 常量类
 */
public class SearchConstans {

    /**
     * 分页常量
     */
    public static final String START = "start";
    public static final String LIMIT = "limit";
    public static final String PAGE = "page";

    /**
     * 返回编码
     */
    public static final String SUCESSS_RETURN_CODE = "0";
    public static final String FAILURE_RETURN_CODE = "-9999";
    public static final String CREATE_FAILURE_STATUS = "UNCREATED";
    public static final String DELETE_FAILURE_STATUS = "UNDELETED";

    /**
     * 标准时间格式
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";//年月日
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";//年月日 时分秒
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";//年月日 时分秒 毫秒

    /**
     * String类型时间格式
     */
    public static final String YYYYMMDD = "yyyyMMdd";//年月日
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";//年月日 时分秒
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";//年月日 时分秒 毫秒

    /**
     * es集群地址
     */
    public static final String CLUSTER_ADDRESS = "127.0.0.1:9500,127.0.0.1:9501,127.0.0.1:9502";

    /**
     * es集群名称
     */
    public static final String CLUSTER_NAME ="es-wslixiaoliang";

    /**
     * Portrait Index 字段名称常量
     */
    public interface Portrait
    {
        public static final String INDEX_NAME ="world.famous_portrait";//索引名称
        public static final String INDEX_TYPE ="famous_portrait";//索引类型

        public static final String PORTRAIT_ID ="portraitId";//头像ID
        public static final String PORTRAIT_NAME ="portraitName";//头像名称
        public static final String FAMOUS_ID ="famousId";//名人ID
        public static final String CHINESE_NAME ="chineseName";//中文名称
        public static final String RELATIVE_LOCATION ="relativeLocation";//相对位置

        public static final String CREATE_TIME ="createTime";//索引更新时间
        public static final String ENGLISH_NAME ="englishName";//英文名
        public static final String SEX ="sex";//性别
        public static final String CAREER ="career";//职业
        public static final String ACHIEVEMENT ="achievement";//主要成就
        public static final String HONOR ="honor";//获得奖项
        public static final String COUNTRY ="country";//国籍
        public static final String BIRTH_YEAR ="birthYear";//生卒年月
        public static final String SUMMARY_INFO ="summaryInfo";//简介
    }

    /**
     * Production index 字段名称常量
     */
    public interface Production
    {
        public static final String INDEX_NAME ="world.famous_production";//索引名称
        public static final String INDEX_TYPE ="famous_production";//索引类型

        public static final String CREATE_TIME ="createTime";//索引更新时间
        public static final String PRODUCTION_ID ="productionId";//作品ID
        public static final String FAMOUS_ID ="famousId";//名人ID
        public static final String PORTRAIT_NAME ="portraitName";//头像名称
        public static final String CHINESE_NAME ="chineseName";//中文名称
        public static final String ENGLISH_NAME ="englishName";//作者英文名
        public static final String PRODUCTION_NAME ="productionName";//作品名称
        public static final String PUBLISHED_YEAR ="publishedYear";//发表年份
        public static final String SUMMARY_INFO ="summaryInfo";//摘要
        public static final String PRODUCTION_CNTT ="productionContent";//作品内容

    }

    /**
     * 搜索提示词
     */
    public interface Suggestions
    {
        public static final String INDEX_NAME = "famous.suggestion_words";
        public static final String INDEX_TYPE = "suggestion_words";
        public static final String SUGGESTION_ID ="suggestionId";//提示词ID
        public static final String SUGGESTION_NAME = "suggestionName";//提示词名称
        public static final String CLICK_TIMES ="clickTimes";//搜索次数
    }


}
