/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.web.component.famous;

import com.art.beans.elastic.SearchResult;
import com.art.util.SearchConstans;
import com.art.web.component.elastic.SearchComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class SearchFamousComponent
{
    @Autowired
    private SearchComponent searchDocumentsComponent;

    /**
     * 查询索引
     * @param fields
     * @return
     */
    public SearchResult searchFamousInfo(Map<String,Object> fields,String includes[],String excludes[],int page ,int limit) throws Exception
    {
        //索引名称和索引类型
        String indexName = SearchConstans.Portrait.INDEX_NAME;
        String indexType = SearchConstans.Portrait.INDEX_TYPE;
        Map<String,Object> params = new HashMap<>();
        params.put("indexName",indexName);
        params.put("indexType",indexType);
        return searchDocumentsComponent.searching(params,fields,includes,excludes,page,limit);
    }



}
