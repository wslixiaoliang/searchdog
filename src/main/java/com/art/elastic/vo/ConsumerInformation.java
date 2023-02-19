/*
 * Copyright © 2016-2021 Liang Searching Group. All rights reserved.
 */

package com.art.elastic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户信息bean
 *
 * @author wslixiaoliang
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
