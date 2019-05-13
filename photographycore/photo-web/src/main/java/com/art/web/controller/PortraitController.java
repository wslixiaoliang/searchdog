package com.art.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.famous.FamousPortrait;
import com.art.service.famous.IFamousPortraitSV;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 名人肖像Controller
 * @author wslixiaoliang
 */
@RestController
@RequestMapping(value = "/portrait")
public class PortraitController {

    @Reference
    private IFamousPortraitSV sv;
    private final Logger logger = Logger.getLogger(PortraitController.class);

    /**
     * 主键查询
     */
    @RequestMapping(value = "/getPortraitInfos")
    public List<FamousPortrait> getPortraitInfos(){

       List<FamousPortrait> portraitList = sv.getPortraitInfos();

       if(null!=portraitList && !portraitList.isEmpty()){
           for(FamousPortrait portrait:portraitList){
               String portraitName = portrait.getPortraitName();
               String locatin = portrait.getPortraitLocation();
               String finalPath = locatin+"/"+portraitName;
               portrait.setFinalPath(finalPath);
               logger.info("=======================名称为："+portraitName+"的肖像完全路径为："+finalPath);
           }

       }

       return portraitList;

    }




}
