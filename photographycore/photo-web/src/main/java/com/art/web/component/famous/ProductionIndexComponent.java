package com.art.web.component.famous;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.elastic.SearchResult;
import com.art.beans.famous.FamousProduction;
import com.art.service.famous.IFamousProductionSV;
import com.art.util.famous.Constans;
import com.art.util.famous.LiangUtil;
import com.art.web.component.elastic.IndexComponent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductionIndexComponent {

    @Autowired
    private IndexComponent indexComponent;
    @Reference
    private IFamousProductionSV famousProductionSV;
    private static final Logger LOGGER = Logger.getLogger(ProductionIndexComponent.class);
    private static int count = 0;

    public SearchResult productionIndex(List<String> famousList)
    {
        SearchResult searchResult = new SearchResult();
        List<FamousProduction>  productionList = this.getProductions( famousList);
        if(null!=productionList && !productionList.isEmpty()){
            List<Map<String,Object>> documents = this.cfgIndexColumn(productionList);
            if(null!=documents && !documents.isEmpty()){
                for(Map<String,Object> document:documents){
                    String id = String.valueOf(document.get(Constans.Production.PRODUCTION_ID));
                    try {
                        searchResult = indexComponent.createOrUpdating(Constans.Production.INDEX_NAME,Constans.Production.INDEX_TYPE,id,document);
                        if(null!=searchResult && Constans.SUCESSS_RETURN_CODE.equals(String.valueOf(searchResult.getReturnCode()))){
                            count++;
                            searchResult.setTotalCount(count);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        LOGGER.error("新增失败……"+e);
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
            LOGGER.error(e);
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
            document.put(Constans.Production.PRODUCTION_ID, LiangUtil.isNotEmpty(String.valueOf(production.getProductionId()))?String.valueOf(production.getProductionId()):"");
            document.put(Constans.Production.FAMOUS_ID, LiangUtil.isNotEmpty(String.valueOf(production.getFamousId()))?String.valueOf(production.getFamousId()):"");
            document.put(Constans.Production.PORTRAIT_NAME, LiangUtil.isNotEmpty(String.valueOf(production.getPortraitName()))?String.valueOf(production.getPortraitName()):"");
            document.put(Constans.Production.CHINESE_NAME, LiangUtil.isNotEmpty(String.valueOf(production.getChineseName()))?String.valueOf(production.getChineseName()):"");
            document.put(Constans.Production.ENGLISH_NAME, LiangUtil.isNotEmpty(String.valueOf(production.getEnglishName()))?String.valueOf(production.getEnglishName()):"");

            document.put(Constans.Production.PRODUCTION_NAME, LiangUtil.isNotEmpty(String.valueOf(production.getProductionName()))?String.valueOf(production.getProductionName()):"");
            document.put(Constans.Production.PUBLISHED_YEAR, LiangUtil.isNotEmpty(String.valueOf(production.getPublishedYear()))?String.valueOf(production.getPublishedYear()):"");
            document.put(Constans.Production.SUMMARY_INFO, LiangUtil.isNotEmpty(String.valueOf(production.getSummaryInfo()))?String.valueOf(production.getSummaryInfo()):"");
            document.put(Constans.Production.PRODUCTION_CNTT, LiangUtil.isNotEmpty(String.valueOf(production.getProductionContent()))?String.valueOf(production.getProductionContent()):"");
            documents.add(document);
        }
        return documents;
    }
}
