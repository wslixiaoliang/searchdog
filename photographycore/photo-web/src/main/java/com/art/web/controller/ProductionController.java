package com.art.web.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.elastic.SearchResult;
import com.art.beans.famous.FamousProduction;
import com.art.beans.famous.Result;
import com.art.service.famous.IFamousProductionSV;
import com.art.util.SearchConstans;
import com.art.util.StringUtil;
import com.art.web.component.famous.SearchProductionComponent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名人作品controller
 * @author wslixiaoliang
 */
@RestController
@RequestMapping(value = "/production")
public class ProductionController {

    @Reference
    private IFamousProductionSV productionSV;
    @Autowired
    SearchProductionComponent searchProductionComponent;
    private final Logger logger = Logger.getLogger(ProductionController.class);

    /**
     * 名人作品：条件查询
     * @param page
     * @param limit
     * @param productionContent
     * @return
     */
    @RequestMapping(value = "/getProductionInfos")
    public Result getProductionInfos(Integer page, Integer limit, String productionContent)
    {
        Result result = new Result();
        List<FamousProduction> productionList;
        try{
            Map<String,Object> fields = new HashMap();

            if(StringUtils.isNotEmpty(productionContent)){
                fields.put("chineseName",productionContent);
                fields.put("productionName",productionContent);
                fields.put("summaryInfo",productionContent);
                fields.put("productionContent",productionContent);
            }

            //调用搜索引擎
            SearchResult searchResult = searchProductionComponent.searchProductions(fields,page,limit);
            List<Map<String,Object>> documents =searchResult.getDocuments();
            int count = searchResult.getTotalCount();
            productionList= map2Bean(documents);

            result.setBeans(productionList);
            result.setCount(count);
            result.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
            result.setReturnMessage("查询成功");

        }catch(Exception e){
            e.printStackTrace();
            logger.info(e.getMessage(),e);
            result.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
            result.setReturnMessage("查询失败");
        }
        return result;
    }


    private List<FamousProduction> map2Bean(List<Map<String,Object>> documents){

        List<FamousProduction> productionList = new ArrayList<>();

        if(null==documents && documents.size()==0){
            return productionList;
        }

        for(Map<String,Object> document:documents){

            long famousId  = Long.parseLong(String.valueOf(document.get("famousId")));//名人ID
            long productionId  = Long.parseLong(String.valueOf(document.get("productionId")));//作品ID

            String portraitName = String.valueOf(document.get("portraitName"));//肖像名称
            String chineseName = String.valueOf(document.get("chineseName"));//中文名
            String englishName = String.valueOf(document.get("englishName"));//英文名

            String productionName = String.valueOf(document.get("productionName"));//作品名称
            String publishedYear = String.valueOf(document.get("publishedYear"));//发表年份
            String summaryInfo = String.valueOf(document.get("summaryInfo"));//作品摘要
            String productionContent = String.valueOf(document.get("productionContent"));//作品内容

            FamousProduction production = new FamousProduction(productionId, famousId, portraitName, chineseName, englishName, productionName, publishedYear, summaryInfo, productionContent);
            productionList.add(production);

        }
        return productionList;
    }



    /**
     * 单个 && 多作品查询
     * @param productionId
     * @return
     */
    @RequestMapping(value = "/getFamousProductionById")
    public List<FamousProduction> getFamousProductionById(String productionId,String famousId,String searchkeyword){
        List<FamousProduction> productions = new ArrayList<>();
        try{
            Map map = new HashMap();
            if(StringUtils.isNotEmpty(productionId)){
                map.put("productionId",productionId);
            }
            if(StringUtils.isNotEmpty(famousId)){
                map.put("famousId",famousId);
            }
            productions = productionSV.getProductionById(map);
            if(null!=productions && productions.size()==1){
                replaceHighLightContent(productions,searchkeyword);
            }
        }catch(Exception e){
            logger.info(e.getMessage(),e);
        }
        return productions;
    }

    /**
     * 详情页高亮替换
     * @param productions
     * @param searchkeyword
     * @return
     */
    private List<FamousProduction> replaceHighLightContent(List<FamousProduction> productions,String searchkeyword){

        if(StringUtil.isEmpty(searchkeyword)){
            return  productions;
        }
        for(FamousProduction production :productions){
            String productionName = replaceHighLightContent(production.getProductionName(),searchkeyword);
            String chineseName = replaceHighLightContent(production.getChineseName(),searchkeyword);
            String productionContent = replaceHighLightContent(production.getProductionContent(),searchkeyword);

            production.setProductionName(productionName);
            production.setChineseName(chineseName);
            production.setProductionContent(productionContent);
        }
        return productions;

    }

    /**
     * 高亮替换
     * @param content
     * @param searchkeyword
     * @return
     */
    private String replaceHighLightContent(String content,String searchkeyword){

        String highLightContent = "<span style=\"color:red\">"+searchkeyword+"</span>";
        String finalConten = "";

        if(content.indexOf(searchkeyword)>=0){

            finalConten=content.replace(searchkeyword,highLightContent);

        }else{
            return content;
        }
        return finalConten;

    }
}
