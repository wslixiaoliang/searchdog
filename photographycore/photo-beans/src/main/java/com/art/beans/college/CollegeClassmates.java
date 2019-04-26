package com.art.beans.college;

import java.io.Serializable;

/**
 * 大学同学bean
 */
public class CollegeClassmates implements Serializable{

    private Long id;//ID
    private String nickName;//昵称
    private String sex;//性别
    private String phoneNumber;//电话
    private String mailBox;//邮箱
    private String address;//地址

    //无参构造方法
    public CollegeClassmates() {
    }

    //有参构造方法

    public CollegeClassmates(Long id, String nickName, String sex, String phoneNumber, String mailBox, String address) {
        this.id = id;
        this.nickName = nickName;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.mailBox = mailBox;
        this.address = address;
    }

    //getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailBox() {
        return mailBox;
    }

    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
