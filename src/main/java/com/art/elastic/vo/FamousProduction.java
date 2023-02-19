/*
 * Copyright © 2016-2021 Liang Searching Group. All rights reserved.
 */

package com.art.elastic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 世界名人作品bean
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamousProduction implements Serializable {

    private static final long serialVersionUID = -4356991497765224784L;
    private long productionId;//作品ID
    private long famousId;//名人ID
    private String portraitName;//肖像名
    private String chineseName;//中文名
    private String englishName;//英文名
    private String productionName;//作品名称
    private String publishedYear;//发表年份
    private String summaryInfo;//作品摘要
    private String productionContent;//作品内容

    //有参构造方法
    public FamousProduction(long productionId, String portraitName, String chineseName, String englishName, String productionName, String publishedYear, String summaryInfo, String productionContent) {
        this.productionId = productionId;
        this.portraitName = portraitName;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.productionName = productionName;
        this.publishedYear = publishedYear;
        this.summaryInfo = summaryInfo;
        this.productionContent = productionContent;
    }
}
