/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.web.component.famous;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.elastic.service.IFamousSuggestionSV;
import com.art.elastic.util.DateUtil;
import com.art.elastic.util.SearchConstans;
import com.art.elastic.util.StringUtil;
import com.art.elastic.vo.SearchResult;
import com.art.elastic.vo.Suggestion;
import com.art.web.component.elastic.IndexComponent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索提示词索引：新增
 * @author wslixiaoliang
 */
@Component
@Slf4j
public class IndexSuggestionComponent {

    @Autowired
    IndexComponent indexComponent;

    @Reference
    IFamousSuggestionSV suggestionSV;

    private static Integer count = 0;

    /**
     * 搜索提示词新增
     * @param suggestionIds
     * @return
     */
    public SearchResult indexSuggestions(List<String> suggestionIds){
        SearchResult searchResult = new SearchResult();
        List<Map<String,Object>> documents = new ArrayList<>();
        if(null==suggestionIds || suggestionIds.isEmpty()){
            return searchResult;
        }
        List<Suggestion> suggestionList = this.getSuggestions(suggestionIds);
        if(null!=suggestionList && !suggestionList.isEmpty()){
            documents = change2Map(suggestionList);
        }
        if(CollectionUtils.isEmpty(documents)){
            return searchResult;
        }
        for(Map<String,Object> document:documents){
            String suggestionId = String.valueOf(document.get(SearchConstans.Suggestions.SUGGESTION_ID));
            try {
                searchResult = indexComponent.createOrUpdating(SearchConstans.Suggestions.INDEX_NAME,SearchConstans.Suggestions.INDEX_TYPE,suggestionId,document);
                if(SearchConstans.SUCESSS_RETURN_CODE.equals(searchResult.getReturnCode())){
                    count++;
                    logger.info("新增索引成功");
                }else{
                    logger.error("新增索引失败");
                }
            } catch (IOException e) {
                logger.error("新增索引失败:{}",e.getMessage());
            }
        }
        searchResult.setTotalCount(count);
        return searchResult;
    }

    /**
     * 从数据库查询：搜索提示词信息
     * @param suggestionIds
     * @return
     */
    private List<Suggestion> getSuggestions(List<String> suggestionIds){
        Map<String,Object> map = new HashMap<>();
        List<Suggestion> suggestionList = new ArrayList<>();
        try{
            if(null!=suggestionIds && !suggestionIds.isEmpty()){
                map.put("suggestionList",suggestionIds);
                suggestionList = suggestionSV.getSuggestions(map);
            }
        }catch(Exception e){
            logger.error("查询失败:{}",e.getMessage());
        }
        return suggestionList;
    }

    /**
     * 实体类转化成Map
     * @param suggestionList
     * @return
     */
    private List<Map<String,Object>> change2Map(List<Suggestion> suggestionList){
        List<Map<String,Object>> documents = new ArrayList<>();
        if(null== suggestionList || suggestionList.isEmpty() ){
            return documents;
        }
        for(Suggestion suggestion:suggestionList){
            Map<String,Object> document = new HashMap<>();
            document.put(SearchConstans.Suggestions.SUGGESTION_ID, StringUtil.isNotEmpty(suggestion.getSuggestionId())?suggestion.getSuggestionId():"");
            document.put(SearchConstans.Suggestions.SUGGESTION_NAME,StringUtil.isNotEmpty(suggestion.getSuggestionName())?suggestion.getSuggestionName():"");
            document.put(SearchConstans.Portrait.CREATE_TIME, DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            document.put(SearchConstans.Suggestions.CLICK_TIMES,StringUtil.isNotEmpty(String.valueOf(suggestion.getClickTimes()))?String.valueOf(suggestion.getClickTimes()):0);
            documents.add(document);
        }
        return documents;
    }


}
