/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.web.controller;

import com.art.elastic.util.CommonUtil;
import com.art.elastic.util.SearchConstans;
import com.art.elastic.util.StringUtil;
import com.art.elastic.vo.SearchResult;
import com.art.elastic.vo.SuggestResult;
import com.art.elastic.vo.Suggestion;
import com.art.web.component.famous.SearchSuggestionComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索提示词controller
 * @author wslixiaoliang
 */
@Slf4j
@RestController
@RequestMapping(value = "/suggest")
public class SuggestionController {

    @Autowired
    SearchSuggestionComponent searchSuggestionComponent;
    private static final String INCLUDES=  "suggestionId,suggestionName,clickTimes";
    private static final String EXCLUDES = "createTime";

    @RequestMapping(value = "/getSuggestions")
    public SuggestResult getSuggestions(String suggestionName){

        SuggestResult result = new SuggestResult();

        Map<String,Object> fields = new HashMap<>();
        if(StringUtil.isNotEmpty(suggestionName)){
            fields.put("suggestionName",suggestionName);
        }
        String[] includes = CommonUtil.includesOrExcludes(INCLUDES);
        String[] excludes = CommonUtil.includesOrExcludes(EXCLUDES);

        try {
            SearchResult searchResult = searchSuggestionComponent.getSuggestions(fields,includes,excludes);
            List<Map<String,Object>> documents = searchResult.getDocuments();
            List<Suggestion> suggestionList = this.map2Bean( documents);

            if(null!=suggestionList && !suggestionList.isEmpty()){
                String [] strings = list2ArrayString(suggestionList);
                result.setSuggestions(strings);//list转array
                result.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
                result.setReturnMessage("查询成功");
                log.info("查询成功");
            }else{
                result.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
                result.setReturnMessage("查询失败");
            }
        } catch (Exception e) {
            log.error("查询失败:{}",e.getMessage());
            result.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
            result.setReturnMessage("查询失败");
        }
        return result;
    }

    /**
     * list转array
     * @param suggestionList
     * @return
     */
    private String[] list2ArrayString(List<Suggestion> suggestionList){
        String [] suggestions;
        List<String> strings = new ArrayList<>();
        for(Suggestion suggestion:suggestionList){
            String suggestName = suggestion.getSuggestionName();
            strings.add(suggestName);
        }
        suggestions = strings.toArray(new String[suggestionList.size()]);
        return suggestions;
    }

    /**
     * Map转化成bean
     * @param documents
     * @return
     */
    private List<Suggestion> map2Bean(List<Map<String,Object>> documents){

        List<Suggestion> suggestionList  = new ArrayList<>();
        if(null==documents || documents.isEmpty()){
            return suggestionList;
        }
        for(Map<String,Object> document:documents){
            Suggestion suggestion = new Suggestion();
            suggestion.setSuggestionId(String.valueOf(document.get(SearchConstans.Suggestions.SUGGESTION_ID)));
            suggestion.setSuggestionName(String.valueOf(document.get(SearchConstans.Suggestions.SUGGESTION_NAME)));
//            suggestion.setClickTimes((Integer)document.get(SearchConstans.Suggestions.CLICK_TIMES));
            suggestionList.add(suggestion);
        }
        return suggestionList;
    }


}
