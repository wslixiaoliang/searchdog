/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.elastic.service;

import java.util.List;
import java.util.Map;

public interface IFamousSuggestionSV {

    /**
     * 根据Id查询 搜索提示词
     * @param map
     * @return
     */
    List<com.art.elastic.vo.Suggestion> getSuggestions(Map map);

}
