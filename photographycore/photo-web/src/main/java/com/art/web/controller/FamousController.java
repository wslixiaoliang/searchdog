package com.art.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.famous.WorldFamous;
import com.art.service.famous.IWorldFamousSV;
import com.art.util.famous.Constans;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 世界名人Controller
 * @author lixiaoliang
 */
@RestController
@RequestMapping(value = "/worldFamous")
public class FamousController {

    @Reference
    private IWorldFamousSV collegeClassmatesSV;
    private static  final Logger logger = Logger.getLogger(FamousController.class);

    /**
     * 根据条件查询：大学同学信息
     * @param famous
     */
    @RequestMapping(value = "/getWorldFamous",method = RequestMethod.POST)
    public List<WorldFamous> getWorldFamous(WorldFamous famous){
        List<WorldFamous> famousList = new ArrayList<>();
        Integer page =1;
        Integer limit = 10;
        try{
            if(null== famous) return null;
            Map<String,Object> queryMap = this.changeBean2Map(famous);
            if(page==null  && limit== null ){
                page = 1;
                limit  = 10;
            }
            queryMap.put(Constans.START,limit*(page-1));
            queryMap.put(Constans.LIMIT,limit);
            famousList= collegeClassmatesSV.getFamousInfos(queryMap);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
        }
        return famousList;
    }

    /**
     * bean 转化城 map
     * @param famous
     * @return
     */
    private Map<String,Object> changeBean2Map(WorldFamous famous){

        String chineseName = famous.getChineseName();
        String sex = famous.getSex();
        String career = famous.getCareer();
        String achievement = famous.getAchievement();
        String honor = famous.getHonor();
        String country = famous.getCountry();

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("chineseName",chineseName);
        queryMap.put("sex",sex);
        queryMap.put("career",career);
        queryMap.put("achievement",achievement);
        queryMap.put("honor",honor);
        queryMap.put("country",country);
        return queryMap;
    }



}
