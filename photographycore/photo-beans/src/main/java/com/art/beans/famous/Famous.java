package com.art.beans.famous;

import java.io.Serializable;

/**
 * 世界名人bean
 */
public class Famous implements Serializable{

    private static final long serialVersionUID = -2999449919136712676L;
    private Long famousId;//名人ID
    private String chineseName;//中文名
    private String englishName;//英文名
    private String sex;//性别
    private String career;//职业
    private String achievement;//主要成就
    private String honor;//获得奖项
    private String country;//国籍
    private String birthYear;//出生年月
    private String portraitName;//肖像名称
    private String relativeLocation;//相对位置


    //无参构造方法
    public Famous() {
    }

    //有参构造方法
    public Famous(Long famousId, String chineseName, String englishName, String sex, String career, String achievement, String honor, String country, String birthYear, String portraitName, String relativeLocation) {
        this.famousId = famousId;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.sex = sex;
        this.career = career;
        this.achievement = achievement;
        this.honor = honor;
        this.country = country;
        this.birthYear = birthYear;
        this.portraitName = portraitName;
        this.relativeLocation = relativeLocation;
    }

    //getter and setter
    public Long getFamousId() {
        return famousId;
    }

    public void setFamousId(Long famousId) {
        this.famousId = famousId;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
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
}
