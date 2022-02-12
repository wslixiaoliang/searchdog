/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.elastic.web.component.famous;

import com.art.elastic.util.SearchConstans;
import com.art.elastic.vo.SearchResult;
import com.art.elastic.web.component.elastic.SearchComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * 搜索提示词查询组件
 * @author wslixiaoliang
 */
@Component
@Slf4j
public class SearchSuggestionComponent {

    @Autowired
    SearchComponent searchComponent;

    /**
     * 根据搜索关键词，查询搜索提示词
     * @param terFields
     * @param includes
     * @param excludes
     * @return
     * @throws Exception
     */
    public SearchResult getSuggestions(Map<String,Object> terFields, String includes[], String excludes[]) throws Exception {
        String indexName = SearchConstans.Suggestions.INDEX_NAME;
        String indexType = SearchConstans.Suggestions.INDEX_TYPE;
        return searchComponent.searching(indexName,indexType,terFields,includes,excludes);
    }
}
