package com.art.web.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.famous.FamousProduction;
import com.art.beans.famous.Result;
import com.art.service.famous.IFamousProductionSV;
import com.art.util.famous.Constans;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private final Logger logger = Logger.getLogger(ProductionController.class);

    /**
     * 名人作品：条件查询
     * @param page
     * @param limit
     * @param production
     * @return
     */
    @RequestMapping(value = "/getProductionInfos")
    public Result getProductionInfos(Integer page, Integer limit, FamousProduction production)
    {
        Result result = new Result();
        List<FamousProduction> productionList ;
        try{
            Map<String,Object> map = new HashMap();
            map.put(Constans.START,limit*(page-1));
            map.put(Constans.LIMIT,limit);
            if(StringUtils.isNotEmpty(production.getChineseName())){
                map.put("chineseName",production.getChineseName());
            }else{
                map.put("chineseName","");
            }
            if(StringUtils.isNotEmpty(production.getProductionName())){
                map.put("productionName",production.getProductionName());
            }else{
                map.put("productionName","");
            }
            productionList = productionSV.getProductionInfos(map);
            Integer count = productionSV.getProductionCount(map);
            result.setBeans(productionList);
            result.setCount(count);
            result.setReturnCode(Constans.SUCESSS_RETURN_CODE);
            result.setReturnMessage("查询成功");

        }catch(Exception e){
            e.printStackTrace();
            logger.info(e.getMessage(),e);
            result.setReturnCode(Constans.FAILURE_RETURN_CODE);
            result.setReturnMessage("查询失败");
        }
        return result;
    }

    /**
     * 主键查询
     * @param productionId
     * @return
     */
    @RequestMapping(value = "/getFamousProductionById")
    public FamousProduction getFamousProductionById(String productionId){
        FamousProduction production = new FamousProduction();
        try{
            if(!StringUtils.isNotEmpty(productionId)){
                return production;
            }
            Map map = new HashMap();
            map.put("productionId",productionId);
            production = productionSV.getProductionById(map);
        }catch(Exception e){
            logger.info(e.getMessage(),e);
        }
        return production;
    }
}
