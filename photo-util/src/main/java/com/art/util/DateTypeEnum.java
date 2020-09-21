/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.util;

/**
 * 时间格式 枚举
 * @author wslixiaoliang
 */
public enum DateTypeEnum {

    YYY_MM("yyyy-MM","yyyy-MM"),
    YYYY_MM_DD("yyyy-MM-dd","yyyy-MM-dd"),
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss");

    private String code;
    private String value;

    DateTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
