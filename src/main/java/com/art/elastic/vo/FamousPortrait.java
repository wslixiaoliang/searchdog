/*
 * Copyright © 2016-2021 Liang Searching Group. All rights reserved.
 */

package com.art.elastic.vo;

import lombok.Data;
import java.io.Serializable;

/**
 * 世界名人&肖像 bean
 *
 * @author wslixiaoliang
 */
@Data
public class FamousPortrait implements Serializable{

    private static final long serialVersionUID = -7636370179239356829L;
    private long portraitId;//肖像ID
    private String portraitName;//肖像名称
    private String relativeLocation;//相对位置
    private long famousId;// 名人ID
    private String chineseName;//中文名
    private String englishName;//英文名
    private String sex;//性别
    private String career;//职业
    private String achievement;//主要成就
    private String honor;//获得奖项
    private String country;//国籍
    private String birthYear;//出生年月
    private String summaryInfo;//简介


    //无参构造方法
    public FamousPortrait() {
    }

    //有参构造方法
    public FamousPortrait(String portraitName,long famousId,String chineseName, String englishName, String sex, String career, String achievement, String honor, String country, String birthYear) {
        this.portraitName = portraitName;
        this.famousId = famousId;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.sex = sex;
        this.career = career;
        this.achievement = achievement;
        this.honor = honor;
        this.country = country;
        this.birthYear = birthYear;
    }

    //有参构造方法
    public FamousPortrait(long famousId,String portraitName,String chineseName,String englishName,String achievement,String birthYear,String summaryInfo){
        this.famousId = famousId;
        this.portraitName = portraitName;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.achievement = achievement;
        this.birthYear = birthYear;
        this.summaryInfo = summaryInfo;
    }

}
