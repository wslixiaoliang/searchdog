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
    public SearchResult searchFamousInfo(Map<String,Object> fields,int page ,int limit) throws Exception
    {
        //索引名称和索引类型
        String indexName = SearchConstans.Portrait.INDEX_NAME;
        String indexType = SearchConstans.Portrait.INDEX_TYPE;
        Map<String,Object> params = new HashMap<>();
        params.put("indexName",indexName);
        params.put("indexType",indexType);
        SearchResult searchResult = searchDocumentsComponent.searching(params,fields,page,limit);

        return searchResult;
    }



}
