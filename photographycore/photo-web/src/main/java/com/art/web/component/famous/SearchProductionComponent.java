package com.art.web.component.famous;

import com.art.beans.elastic.SearchResult;
import com.art.util.SearchConstans;
import com.art.web.component.elastic.SearchComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class SearchProductionComponent {

    @Autowired
    private SearchComponent searchDocumentsComponent;

    /**
     * 查询索引
     * @param fields
     * @return
     */
    public SearchResult searchProductions(Map<String,Object> fields,int page,int limit) throws Exception {
        String indexName = SearchConstans.Production.INDEX_NAME;//索引名称
        String indexType = SearchConstans.Production.INDEX_TYPE;//和索引类型
        Map<String,Object> params = new HashMap<>();
        params.put("indexName",indexName);
        params.put("indexType",indexType);
        SearchResult searchResult = searchDocumentsComponent.searching(params,fields,page,limit);
        return searchResult;
    }

}
