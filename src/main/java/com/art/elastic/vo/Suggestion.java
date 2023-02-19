/*
 * Copyright © 2016-2021 Liang Searching Group. All rights reserved.
 */

package com.art.elastic.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 搜索提示词 实体类
 *
 * @author wslixiaoliang
 */
@Data
public class Suggestion implements Serializable {

    private String suggestionId;
    private String suggestionName;
    private int clickTimes;
    private String createTime;
}
