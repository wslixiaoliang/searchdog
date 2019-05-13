package com.art.beans.famous;

import java.io.Serializable;

/**
 * 世界名人肖像bean
 * @author wslixiaoliang
 */
public class FamousPortrait implements Serializable{

    private long portraitId;//肖像ID
    private long famousId;// 名人ID
    private String portraitName;//肖像名称
    private String portraitLocation;//肖像相对位置
    private String portraitSummary;//简介

    //无参构造方法
    public FamousPortrait() {
    }

    //有参构造方法
    public FamousPortrait(long portraitId, long famousId, String portraitName, String portraitLocation, String portraitSummary) {
        this.portraitId = portraitId;
        this.famousId = famousId;
        this.portraitName = portraitName;
        this.portraitLocation = portraitLocation;
        this.portraitSummary = portraitSummary;
    }

    //getter and setter
    public long getPortraitId() {
        return portraitId;
    }

    public void setPortraitId(long portraitId) {
        this.portraitId = portraitId;
    }

    public long getFamousId() {
        return famousId;
    }

    public void setFamousId(long famousId) {
        this.famousId = famousId;
    }

    public String getPortraitName() {
        return portraitName;
    }

    public void setPortraitName(String portraitName) {
        this.portraitName = portraitName;
    }

    public String getPortraitLocation() {
        return portraitLocation;
    }

    public void setPortraitLocation(String portraitLocation) {
        this.portraitLocation = portraitLocation;
    }

    public String getPortraitSummary() {
        return portraitSummary;
    }

    public void setPortraitSummary(String portraitSummary) {
        this.portraitSummary = portraitSummary;
    }
}
