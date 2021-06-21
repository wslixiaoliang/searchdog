/*
 * Copyright © 2016-2021 Liang Searching Group. All rights reserved.
 */

package com.art.beans.famous;

import java.io.Serializable;

/**
 * 搜索提示词 实体类
 *
 * @author wslixiaoliang
 */
public class Suggestion implements Serializable {


    private String suggestionId;
    private String suggestionName;
    private int clickTimes;
    private String createTime;


    public Suggestion() {
    }

    public Suggestion(String suggestionId, String suggestionName, int clickTimes, String createTime) {
        this.suggestionId = suggestionId;
        this.suggestionName = suggestionName;
        this.clickTimes = clickTimes;
        this.createTime = createTime;
    }

    public String getSuggestionId() {
        return suggestionId;
    }

    public void setSuggestionId(String suggestionId) {
        this.suggestionId = suggestionId;
    }

    public int getClickTimes() {
        return clickTimes;
    }

    public String getSuggestionName() {
        return suggestionName;
    }

    public void setSuggestionName(String suggestionName) {
        this.suggestionName = suggestionName;
    }

    public void setClickTimes(int clickTimes) {
        this.clickTimes = clickTimes;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
