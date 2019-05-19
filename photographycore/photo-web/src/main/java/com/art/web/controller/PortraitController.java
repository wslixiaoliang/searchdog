package com.art.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.famous.FamousPortrait;
import com.art.service.famous.IFamousPortraitSV;
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
    public List<FamousPortrait> getPortraitInfos(String chineseName)
    {
        Map map = new HashMap();
        map.put("chineseName",chineseName);
        List<FamousPortrait> portraitList = famousPortraitSV.getPortraitInfos(map);

       return portraitList;
    }

}
