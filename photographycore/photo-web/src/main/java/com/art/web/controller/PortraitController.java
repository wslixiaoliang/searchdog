package com.art.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.famous.FamousPortrait;
import com.art.beans.famous.Result;
import com.art.service.famous.IFamousPortraitSV;
import com.art.util.Constans;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名人肖像Controller
 * @author wslixiaoliang
 */
@RestController
@RequestMapping(value = "/portrait")
public class PortraitController {

    @Reference
    private IFamousPortraitSV famousPortraitSV;
    private final Log logger = LogFactory.getLog(PortraitController.class);

    /**
     * 首页肖像信息：条件查询
     */
    @RequestMapping(value = "/getPortraitInfos")
    public Result getPortraitInfos(String chineseName)
    {
        Result result = new Result();
        Map map = new HashMap();
        try{
            map.put("chineseName",chineseName);
            List<FamousPortrait> portraitList = famousPortraitSV.getPortraitInfos(map);
            if(null!=portraitList && !portraitList.isEmpty()){
                result.setBeans(portraitList);
                result.setReturnCode(Constans.SUCESSS_RETURN_CODE);
                result.setReturnMessage("查询成功");
            }
        }catch(Exception e){
            logger.info(e.getMessage(),e);
            result.setReturnCode(Constans.FAILURE_RETURN_CODE);
            result.setReturnMessage("查询失败");
        }
       return result;
    }
}
