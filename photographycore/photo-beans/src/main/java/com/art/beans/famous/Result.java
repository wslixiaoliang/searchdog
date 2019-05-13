package com.art.beans.famous;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {


    private String returnCode;//返回编码
    private String returnMsg;//返回信息
    private List beans;//返回值
    private int count;//返回数量

    //无参构造方法

    public Result() {
    }


    //有参构造方法
    public Result(String returnCode, String returnMsg, List beans, int count) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.beans = beans;
        this.count = count;
    }

    //getter and setter
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

    public List getBeans() {
        return beans;
    }

    public void setBeans(List beans) {
        this.beans = beans;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
