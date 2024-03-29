/*
 * Copyright © 2016-2021 Liang Searching Group. All rights reserved.
 */

package com.art.elastic.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 结果集对象
 *
 * @author wslixiaoliang
 */
@Data
public class Result<T> implements Serializable{

    private static final long serialVersionUID = 4049838581798497729L;
    private List<T> beans;
    private int count;
    private String returnCode;
    private String returnMessage;
}
