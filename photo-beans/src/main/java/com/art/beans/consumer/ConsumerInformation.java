/*
 * Copyright © 2016-2021 Liang Searching Group. All rights reserved.
 */

package com.art.beans.consumer;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户信息bean
 *
 * @author wslixiaoliang
 */

public class ConsumerInformation implements Serializable {

    private static final long serialVersionUID = -8870130587737173056L;
    private String consumerId;//用户ID
    private String consumerName;//名字/账号
    private String sex;//性别
    private String birthday;//生日
    private String city;//归属地市
    private String sign;//个性签名
    private String phoneNumber;//电话/账号
    private String consumerPassword;//密码
    private Timestamp registTime;//注册时间

    //无参构造方法
    public ConsumerInformation() {
    }

    //有参构造方法

    public ConsumerInformation(String consumerId, String consumerName, String sex, String birthday, String city, String sign, String phoneNumber, String consumerPassword, Timestamp registTime) {
        this.consumerId = consumerId;
        this.consumerName = consumerName;
        this.sex = sex;
        this.birthday = birthday;
        this.city = city;
        this.sign = sign;
        this.phoneNumber = phoneNumber;
        this.consumerPassword = consumerPassword;
        this.registTime = registTime;
    }


    //getters and setters

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getConsumerPassword() {
        return consumerPassword;
    }

    public void setConsumerPassword(String consumerPassword) {
        this.consumerPassword = consumerPassword;
    }

    public Timestamp getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Timestamp registTime) {
        this.registTime = registTime;
    }
}
