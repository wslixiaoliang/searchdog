package com.art.web.component.famous;

import com.art.beans.elastic.SearchResult;
import com.art.util.SearchConstans;
import com.art.web.component.elastic.SearchComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * 搜索提示词查询组件
 * @author wslixiaoliang
 */
@Component
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
