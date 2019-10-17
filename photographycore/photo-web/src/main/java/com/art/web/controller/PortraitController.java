package com.art.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.elastic.SearchResult;
import com.art.beans.famous.FamousPortrait;
import com.art.beans.famous.Result;
import com.art.service.famous.IFamousPortraitSV;
import com.art.util.SearchConstans;
import com.art.util.StringUtil;
import com.art.web.component.famous.FamousIndexComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    @Autowired
    FamousIndexComponent famousIndexComponent;
    private final Log logger = LogFactory.getLog(PortraitController.class);

    /**
     * 首页肖像信息：条件查询
     */
    @RequestMapping(value = "/getPortraitInfos")
    public Result getPortraitInfos(String chineseName)
    {
        Result result = new Result();
        Map<String,Object> fields = new HashMap();
        try{
            if(StringUtil.isNotEmpty(chineseName)){
                fields.put("chineseName",chineseName);
            }

//            List<FamousPortrait> portraitList = famousPortraitSV.getPortraitInfos(map);

            //调用搜索引擎
            SearchResult searchResult = famousIndexComponent.searchFamousInfo(fields);
            List<Map<String,Object>> documents =searchResult.getDocuments();
            List<FamousPortrait> portraitList= map2Bean(documents);

            if(null!=portraitList && !portraitList.isEmpty()){
                result.setBeans(portraitList);
                result.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
                result.setReturnMessage("查询成功");
            }
        }catch(Exception e){
            logger.info(e.getMessage(),e);
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
    private List<FamousPortrait> map2Bean(List<Map<String,Object>> documents){

        List<FamousPortrait>famousList = new ArrayList<>();
        if(null==documents && documents.size()==0){
            return famousList;
        }
        for(Map<String,Object> document:documents){
            String portraitId = String.valueOf(document.get("portraitId"));
            String portraitName = String.valueOf(document.get("portraitName"));
            String relativeLocation= String.valueOf(document.get("relativeLocation"));
            String famousId= String.valueOf(document.get("famousId"));
            String chineseName= String.valueOf(document.get("chineseName"));
            String englishName= String.valueOf(document.get("englishName"));
            String sex= String.valueOf(document.get("sex"));
            String career= String.valueOf(document.get("career"));
            String achievement= String.valueOf(document.get("achievement"));
            String honor= String.valueOf(document.get("honor"));
            String country= String.valueOf(document.get("country"));
            String birthYear= String.valueOf(document.get("birthYear"));
            FamousPortrait famousPortrait = new FamousPortrait(Long.valueOf(portraitId),portraitName,relativeLocation,Long.valueOf(famousId),chineseName,englishName,sex,career,achievement,honor,country,birthYear);
            famousList.add(famousPortrait);
        }
        return famousList;


    }
}
