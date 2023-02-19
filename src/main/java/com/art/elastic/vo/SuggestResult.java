/*
 * Copyright © 2016-2021 Liang Searching Group. All rights reserved.
 */

package com.art.elastic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 搜索提示词
 *
 * @author wslixiaoliang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestResult implements Serializable {

    private String [] suggestions;
    private String returnCode;
    private String returnMessage;
}
