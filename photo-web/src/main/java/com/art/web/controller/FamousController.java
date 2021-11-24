/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.elastic.service.IFamousPortraitSV;
import com.art.elastic.util.SearchConstans;
import com.art.elastic.util.StringUtil;
import com.art.elastic.vo.FamousPortrait;
import com.art.elastic.vo.Result;
import com.art.web.component.famous.SearchFamousComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名人维护页：Controller
 * @author lixiaoliang
 */
@Slf4j
@RestController
@RequestMapping(value = "/worldFamous")
public class FamousController {
    @Autowired
    private SearchFamousComponent searchFamousComponent;
    @Reference
    private IFamousPortraitSV famousPortraitSV;
    /**
     * 管理页面：条件查询
     */
    @RequestMapping(value = "/getWorldFamous", method = RequestMethod.POST)
    public Result getWorldFamous(String  chineseName, String  englishName, String  country, String  career, String  sex, int  page, int  limit) {

        Result result = new Result();
        List<FamousPortrait> famousList;
        Map<String,Object> params = new HashMap<>();
        getParams (chineseName,englishName,country,career,sex,page ,limit,params);

        try {
            famousList = famousPortraitSV.getPortraitInfos(params);
            result.setBeans(famousList);
            int count = famousPortraitSV.getFamousCount(params);
            result.setCount(count);
            result.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
            result.setReturnMessage("查询成功");
        } catch (Exception e) {
            log.info("查询失败:{}",e.getMessage());
            result.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
            result.setReturnMessage("查询失败");
        }
        return result;
    }

    /**
     * Map转bean
     * @param documents
     * @return
     */
    private List<FamousPortrait> map2Bean(List<Map<String, Object>> documents) {

        List<FamousPortrait> famousList = new ArrayList<>();
        if (null == documents && documents.size() == 0) {
            return famousList;
        }
        for (Map<String, Object> document : documents) {
            long famousId = Long.parseLong(String.valueOf(document.get("famousId")));
            String portraitName = String.valueOf(document.get("portraitName"));
            String chineseName = String.valueOf(document.get("chineseName"));
            String englishName = String.valueOf(document.get("englishName"));
            String sex = String.valueOf(document.get("sex"));
            String career = String.valueOf(document.get("career"));
            String achievement = String.valueOf(document.get("achievement"));
            String honor = String.valueOf(document.get("honor"));
            String country = String.valueOf(document.get("country"));
            String birthYear = String.valueOf(document.get("birthYear"));
            FamousPortrait famousPortrait = new FamousPortrait(portraitName,famousId,chineseName, englishName, sex, career, achievement, honor, country, birthYear);
            famousList.add(famousPortrait);
        }
        return famousList;

    }


    /**
     * 组装params入参
     * @param chineseName
     * @param englishName
     * @param country
     * @param career
     * @param sex
     * @param params
     */
    private void getParams (String  chineseName,String  englishName,String country,String  career,String  sex,int page,int limit,Map<String,Object> params){
        if(StringUtil.isNotEmpty(chineseName)){
            params.put("chineseName",chineseName);
        }
        if(StringUtil.isNotEmpty(englishName)){
            params.put("englishName",englishName);
        }
        if(StringUtil.isNotEmpty(country)){
            params.put("country",country);
        }
        if(StringUtil.isNotEmpty(career)){
            params.put("career",career);
        }
        if(0!=page){
            params.put("start",(page-1)*limit);
        }
        if(0!=limit){
            params.put("limit",limit);
        }

        if(!"0".equalsIgnoreCase(sex)) {
            String finalSex = getChinesenage(sex);
            params.put("sex",finalSex);
        }
    }

    /**
     * 性别翻译
     * @param sex
     * @return
     */
    private String getChinesenage(String sex){

        String finalSex = "";
        if(StringUtil.isNotEmpty(sex)){
            if("1".equalsIgnoreCase(sex)){
                finalSex = "男";
            }
            if("2".equalsIgnoreCase(sex)){
                finalSex = "女";
            }
            if("3".equalsIgnoreCase(sex)){
                finalSex = "其他";
            }
        }
        return finalSex;
    }

}