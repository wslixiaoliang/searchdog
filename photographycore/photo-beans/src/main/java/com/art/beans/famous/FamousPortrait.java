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
    private String relativeLocation;//相对位置
    private String finalPath;//完全路径
    private String chineseName;//中文名

    //无参构造方法
    public FamousPortrait() {
    }

    //有参构造方法
    public FamousPortrait(long portraitId, long famousId, String portraitName, String relativeLocation, String finalPath, String chineseName) {
        this.portraitId = portraitId;
        this.famousId = famousId;
        this.portraitName = portraitName;
        this.relativeLocation = relativeLocation;
        this.finalPath = finalPath;
        this.chineseName = chineseName;
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

    public String getRelativeLocation() {
        return relativeLocation;
    }

    public void setRelativeLocation(String relativeLocation) {
        this.relativeLocation = relativeLocation;
    }

    public String getFinalPath() {
        return finalPath;
    }

    public void setFinalPath(String finalPath) {
        this.finalPath = finalPath;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }
}
