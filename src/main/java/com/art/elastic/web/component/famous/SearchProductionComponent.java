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

import java.util.HashMap;
import java.util.Map;

/**
 * 文章：全文搜索组建
 * @author wslixiaoliang
 */
@Component
@Slf4j
public class SearchProductionComponent {

    @Autowired
    private SearchComponent searchDocumentsComponent;

    /**
     * 文章：精确匹配查询
     * @param terFields
     * @param includes
     * @param excludes
     * @param page
     * @param limit
     * @return
     * @throws Exception
     */
    public SearchResult searchProductions(Map<String,Object> terFields, String includes[], String excludes[], int page, int limit) throws Exception {
        String indexName = SearchConstans.Production.INDEX_NAME;//索引名称
        String indexType = SearchConstans.Production.INDEX_TYPE;//和索引类型
        Map<String,Object> indexParams = new HashMap<>();
        indexParams.put("indexName",indexName);
        indexParams.put("indexType",indexType);
        return searchDocumentsComponent.searching(indexParams,terFields,includes,excludes,page,limit);
    }

}
