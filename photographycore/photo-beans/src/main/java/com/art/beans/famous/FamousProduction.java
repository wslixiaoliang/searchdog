package com.art.beans.famous;

import java.io.Serializable;

/**
 * 世界名人作品bean
 */
public class FamousProduction implements Serializable {

    private long productionId;//作品ID
    private long famousId;//名人ID
    private String productionName;//作品名称
    private String publishedYear;//发表年份
    private String productionContent;//作品内容

    //无参构造方法
    public FamousProduction() {
    }

    //有参构造方法
    public FamousProduction(long productionId, long famousId, String productionName, String publishedYear, String productionContent) {
        this.productionId = productionId;
        this.famousId = famousId;
        this.productionName = productionName;
        this.publishedYear = publishedYear;
        this.productionContent = productionContent;
    }

    //getter and setter
    public long getProductionId() {
        return productionId;
    }

    public void setProductionId(long productionId) {
        this.productionId = productionId;
    }

    public long getFamousId() {
        return famousId;
    }

    public void setFamousId(long famousId) {
        this.famousId = famousId;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(String publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getProductionContent() {
        return productionContent;
    }

    public void setProductionContent(String productionContent) {
        this.productionContent = productionContent;
    }
}
