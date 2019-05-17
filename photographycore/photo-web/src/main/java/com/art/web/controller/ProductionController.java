package com.art.web.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.famous.FamousProduction;
import com.art.service.famous.IFamousProductionSV;
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

    /**
     * 名人作品：条件查询
     * @param famousId
     * @return
     */
    @RequestMapping(value = "/getProductionInfos")
    public List<FamousProduction> getProductionInfos(String famousId)
    {
        List<FamousProduction> productionList = new ArrayList<>();
        try{
            if(StringUtils.isNotEmpty(famousId)){
                Map<String,Object> map = new HashMap();
                map.put("famousId",Long.valueOf(famousId));
                productionList = productionSV.getProductionInfos(map);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return productionList;
    }
}
