package com.art.elastic.dao;

import com.art.elastic.vo.Suggestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 搜索提示词DAO
 */
public interface FamousSuggestionsMapper {

    /**
     * 根据Id查询 搜索提示词
     * @param map
     * @return
     */
    List<Suggestion> getSuggestions(Map map);
}
