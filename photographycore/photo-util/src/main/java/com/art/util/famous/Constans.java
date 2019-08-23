package com.art.util.famous;

/**
 * 常量类
 */
public class Constans {

    /**
     * 分页常量
     */
    public static final String START = "start";
    public static final String LIMIT = "limit";

    /**
     * 返回编码
     */
    public static final String SUCESSS_RETURN_CODE = "0";
    public static final String FAILURE_RETURN_CODE = "-9999";
    public static final String CREATE_FAILURE_STATUS = "UNCREATED";
    public static final String DELETE_FAILURE_STATUS = "UNDELETED";

    /**
     * 日期格式
     */

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";//年月日 时分秒
    public static final String YYYY_MM_DD = "yyyy-MM-dd";//年月日

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
    }


    /**
     * Production index 字段名称常量
     */
    public interface Production
    {
        public static final String INDEX_NAME ="world.famous_production";//索引名称
        public static final String INDEX_TYPE ="famous_producion";//索引类型

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

}
