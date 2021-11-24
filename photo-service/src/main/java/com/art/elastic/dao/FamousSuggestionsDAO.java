package com.art.elastic.dao;

import com.art.elastic.vo.Suggestion;
import java.util.List;
import java.util.Map;

/**
 * 搜索提示词DAO
 */
public interface FamousSuggestionsDAO {

    /**
     * 根据Id查询 搜索提示词
     * @param map
     * @return
     */
    List<Suggestion> getSuggestions(Map map);
}
