/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.elastic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.art.elastic.dao.FamousSuggestionsMapper;
import com.art.elastic.vo.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

@Service
public class FamousSuggestionSVImpl implements IFamousSuggestionSV{

    @Autowired
    FamousSuggestionsMapper dao;

    /**
     * 根据Id查询 搜索提示词
     * @param map
     * @return
     */
    @Override
    public List<Suggestion> getSuggestions(Map map) {
        return dao.getSuggestions(map);
    }
}
