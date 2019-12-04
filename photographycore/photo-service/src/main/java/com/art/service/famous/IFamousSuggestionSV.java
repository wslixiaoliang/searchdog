package com.art.service.famous;

import com.art.beans.famous.Suggestion;

import java.util.List;
import java.util.Map;

public interface IFamousSuggestionSV {

    /**
     * 根据Id查询 搜索提示词
     * @param map
     * @return
     */
    List<Suggestion> getSuggestions(Map map);

}
