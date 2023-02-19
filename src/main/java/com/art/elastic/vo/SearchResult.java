/*
 * Copyright © 2016-2021 Liang Searching Group. All rights reserved.
 */

package com.art.elastic.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 搜索引擎结果集
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult implements Serializable{
    private static final long serialVersionUID = 693213281152163875L;
    private List<Map<String, Object>> documents;//结果集
    private String status;//状态(如:created)
    private int totalCount;//总条数
    private String returnCode;//返回状态码
    private String returnMsg;//结果描述
}
