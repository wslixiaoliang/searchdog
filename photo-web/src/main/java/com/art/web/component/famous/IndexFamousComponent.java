/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.web.component.famous;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.elastic.service.IFamousPortraitSV;
import com.art.elastic.util.DateUtil;
import com.art.elastic.util.SearchConstans;
import com.art.elastic.util.StringUtil;
import com.art.elastic.vo.FamousPortrait;
import com.art.elastic.vo.SearchResult;
import com.art.web.component.elastic.IndexComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;

/**
 * 新增名人索引
 * @author wslixiaoliang
 */
@Component
@Slf4j
public class IndexFamousComponent {

    @Autowired
    private IndexComponent indexFamousComponent;
    @Reference
    private IFamousPortraitSV famousPortraitSV;
    private static int count = 0;

    public SearchResult indexFamous(List<String> famousIds)
    {
        SearchResult searchResult = new SearchResult();
        List<FamousPortrait> famousList = getFamous(famousIds);//查询portraitList
        List<Map<String,Object>> documents = cfgIndexColumn(famousList);//组装索引字段

        if(CollectionUtils.isEmpty(documents)){
            return searchResult;
        }
        for( Map<String,Object> document:documents){
            if(null!=document && document.containsKey(SearchConstans.Portrait.FAMOUS_ID)){
                String famousId = String.valueOf(document.get(SearchConstans.Portrait.FAMOUS_ID));
                try {
                    searchResult = indexFamousComponent.createOrUpdating(SearchConstans.Portrait.INDEX_NAME, SearchConstans.Portrait.INDEX_TYPE,famousId,document);
                    if(SearchConstans.SUCESSS_RETURN_CODE.equals(String.valueOf(searchResult.getReturnCode()))){
                        count++;
                        searchResult.setTotalCount(count);
                    }
                }catch(IOException e){
                    logger.error("新增索引失败,IO异常:{}",e.getMessage());
                }
            }
        }
        return searchResult;
    }

    /**
     * 根据多Id 查询famous对象
     * @param famousIds
     * @return famousList
     */
    private List<FamousPortrait> getFamous(List<String> famousIds)
    {
        List<FamousPortrait> famousPortraitList = new ArrayList<>();
        Map<String,Object> famousMap = new HashMap<>();
        try{
            if(null!=famousIds && !famousIds.isEmpty()) {
                famousMap.put("famousList",famousIds);
                famousPortraitList = famousPortraitSV.getfamousListByIds(famousMap);
            }
        }catch(Exception e){
            logger.error("查询失败:{}",e.getMessage());
        }
        return famousPortraitList;
    }

    /**
     * 组装索引字段
     * @param famousPortraitList famousPortraitList
     * @return documents
     */
    private List<Map<String,Object>> cfgIndexColumn(List<FamousPortrait> famousPortraitList){
        List<Map<String,Object>> documents = new ArrayList<>();
        if(null==famousPortraitList ||famousPortraitList.isEmpty()){
            return documents;
        }
        for(FamousPortrait famous :famousPortraitList){
            Map<String,Object> document = new HashMap<>();
            document.put(SearchConstans.Portrait.PORTRAIT_ID, StringUtil.isNotEmpty(String.valueOf(famous.getPortraitId()))?String.valueOf(famous.getPortraitId()):"");
            document.put(SearchConstans.Portrait.PORTRAIT_NAME,StringUtil.isNotEmpty(famous.getPortraitName())?famous.getPortraitName():"");
            document.put(SearchConstans.Portrait.FAMOUS_ID,StringUtil.isNotEmpty(String.valueOf(famous.getFamousId()))?String.valueOf(famous.getFamousId()):"");
            document.put(SearchConstans.Portrait.CHINESE_NAME,StringUtil.isNotEmpty(famous.getChineseName())?famous.getChineseName():"");
            document.put(SearchConstans.Portrait.RELATIVE_LOCATION,StringUtil.isNotEmpty(famous.getRelativeLocation())?famous.getRelativeLocation():"");

            document.put(SearchConstans.Portrait.CREATE_TIME, DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            document.put(SearchConstans.Portrait.ENGLISH_NAME, StringUtil.isNotEmpty(famous.getEnglishName())?famous.getEnglishName():"");
            document.put(SearchConstans.Portrait.SEX,StringUtil.isNotEmpty(famous.getSex())?famous.getSex():"");
            document.put(SearchConstans.Portrait.CAREER,StringUtil.isNotEmpty(famous.getCareer())?famous.getCareer():"");
            document.put(SearchConstans.Portrait.ACHIEVEMENT,StringUtil.isNotEmpty(famous.getAchievement())?famous.getAchievement():"");
            document.put(SearchConstans.Portrait.HONOR,StringUtil.isNotEmpty(famous.getHonor())?famous.getHonor():"");
            document.put(SearchConstans.Portrait.COUNTRY,StringUtil.isNotEmpty(famous.getCountry())?famous.getCountry():"");
            document.put(SearchConstans.Portrait.BIRTH_YEAR,StringUtil.isNotEmpty(famous.getBirthYear())?famous.getBirthYear():"");
            document.put(SearchConstans.Portrait.SUMMARY_INFO,StringUtil.isNotEmpty(famous.getSummaryInfo())?famous.getSummaryInfo():"");
            documents.add(document);
        }
        return documents;
    }


}
