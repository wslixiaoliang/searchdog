package com.art.service.famous;

import com.alibaba.dubbo.config.annotation.Service;
import com.art.beans.famous.Suggestion;
import com.art.dao.famous.FamousSuggestionsDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class FamousSuggestionSVImpl implements IFamousSuggestionSV{

    @Autowired
    FamousSuggestionsDAO dao;

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
