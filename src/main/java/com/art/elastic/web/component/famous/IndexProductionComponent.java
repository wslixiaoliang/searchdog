/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.elastic.web.component.famous;

import com.art.elastic.service.IFamousProductionSV;
import com.art.elastic.util.DateUtil;
import com.art.elastic.util.SearchConstans;
import com.art.elastic.util.StringUtil;
import com.art.elastic.vo.FamousProduction;
import com.art.elastic.vo.SearchResult;
import com.art.elastic.web.component.elastic.IndexComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class IndexProductionComponent {

    @Autowired
    private IndexComponent indexComponent;

    @Autowired
    private IFamousProductionSV famousProductionSV;

    private static Integer count = 0;

    public SearchResult productionIndex(List<String> famousList)
    {
        SearchResult searchResult = new SearchResult();
        List<FamousProduction>  productionList = this.getProductions( famousList);
        if(null!=productionList && !productionList.isEmpty()){
            List<Map<String,Object>> documents = this.cfgIndexColumn(productionList);
            if(null!=documents && !documents.isEmpty()){
                for(Map<String,Object> document:documents){
                    String id = String.valueOf(document.get(SearchConstans.Production.PRODUCTION_ID));
                    try {
                        searchResult = indexComponent.createOrUpdating(SearchConstans.Production.INDEX_NAME, SearchConstans.Production.INDEX_TYPE,id,document);
                        if(null!=searchResult && SearchConstans.SUCESSS_RETURN_CODE.equals(String.valueOf(searchResult.getReturnCode()))){
                            count++;
                            searchResult.setTotalCount(count);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        logger.error("新增失败:{}",e.getMessage());
                    }
                }
            }
        }
        return searchResult;
    }

    /**
     * 获取数据库：产品信息List
     * @param famousList
     * @return
     */
    private List<FamousProduction> getProductions(List<String> famousList)
    {
        List<FamousProduction> productionList = new ArrayList<>();
        Map<String,Object> param = new HashMap<>();
        param.put("famousList",famousList);
        try{
            productionList = famousProductionSV.getProductionById(param);
        }catch(Exception e){
            logger.error("查询失败:{}",e.getMessage());
        }
        return productionList;
    }

    /**
     * 组装Document
     * @param productionList
     * @return
     */
    private List<Map<String,Object>> cfgIndexColumn(List<FamousProduction> productionList)
    {
        List<Map<String,Object>> documents = new ArrayList<>();
        if(null==productionList && productionList.isEmpty()){
            return documents;
        }
        for(FamousProduction production:productionList)
        {
            Map<String,Object> document = new HashMap<>();
            document.put(SearchConstans.Production.PRODUCTION_ID, StringUtil.isNotEmpty(String.valueOf(production.getProductionId()))?String.valueOf(production.getProductionId()):"");
            document.put(SearchConstans.Production.FAMOUS_ID, StringUtil.isNotEmpty(String.valueOf(production.getFamousId()))?String.valueOf(production.getFamousId()):"");
            document.put(SearchConstans.Production.PORTRAIT_NAME, StringUtil.isNotEmpty(String.valueOf(production.getPortraitName()))?String.valueOf(production.getPortraitName()):"");
            document.put(SearchConstans.Production.CHINESE_NAME, StringUtil.isNotEmpty(String.valueOf(production.getChineseName()))?String.valueOf(production.getChineseName()):"");
            document.put(SearchConstans.Production.ENGLISH_NAME, StringUtil.isNotEmpty(String.valueOf(production.getEnglishName()))?String.valueOf(production.getEnglishName()):"");
            document.put(SearchConstans.Production.CREATE_TIME, DateUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
            document.put(SearchConstans.Production.PRODUCTION_NAME, StringUtil.isNotEmpty(String.valueOf(production.getProductionName()))?String.valueOf(production.getProductionName()):"");
            document.put(SearchConstans.Production.PUBLISHED_YEAR, StringUtil.isNotEmpty(String.valueOf(production.getPublishedYear()))?String.valueOf(production.getPublishedYear()):"");
            document.put(SearchConstans.Production.SUMMARY_INFO, StringUtil.isNotEmpty(String.valueOf(production.getSummaryInfo()))?String.valueOf(production.getSummaryInfo()):"");
            document.put(SearchConstans.Production.PRODUCTION_CNTT, StringUtil.isNotEmpty(String.valueOf(production.getProductionContent()))?String.valueOf(production.getProductionContent()):"");
            documents.add(document);
        }
        return documents;
    }
}
