package com.art.beans.elastic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 搜索引擎结果集
 */
public class SearchResult implements Serializable{

    private static final long serialVersionUID = 693213281152163875L;
    private List<Map<String, Object>> documents;//结果集
    private String status;//状态(如:created)
    private int totalCount;//总条数
    private String returnCode;//返回状态码
    private String returnMsg;//结果描述

    //无参构造方法
    public SearchResult() {
    }
    //有参构造方法
    public SearchResult(List<Map<String, Object>> documents, String status, int totalCount, String returnCode, String returnMsg) {
        this.documents = documents;
        this.status = status;
        this.totalCount = totalCount;
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    //getters and setters

    public List<Map<String, Object>> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Map<String, Object>> documents) {
        this.documents = documents;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }
}
