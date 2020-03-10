package com.art.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.elastic.SearchResult;
import com.art.beans.famous.FamousPortrait;
import com.art.beans.famous.Result;
import com.art.service.famous.IFamousPortraitSV;
import com.art.util.CommonUtil;
import com.art.util.SearchConstans;
import com.art.util.StringUtil;
import com.art.web.component.famous.IndexFamousComponent;
import com.art.web.component.famous.SearchFamousComponent;
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
    SearchFamousComponent searchFamousComponent;

    private final Log logger = LogFactory.getLog(PortraitController.class);
    private static final String INCLUDES=  "famousId,portraitName,chineseName,englishName,achievement,birthYear,summaryInfo";
    private static final String EXCLUDES = "createTime,relativeLocation,sex,career,honor,country,honor";

    /**
     * 首页肖像信息：条件查询
     */
    @RequestMapping(value = "/getPortraitInfos")
    public Result getPortraitInfos(String chineseName,String famousId)
    {
        Result result = new Result();
        Map<String,Object> fields = new HashMap();

        try{
            if(StringUtil.isNotEmpty(chineseName)){
                fields.put("chineseName",chineseName);
            }
            if(StringUtil.isNotEmpty(famousId)){
                fields.put("famousId",famousId);
            }

            //需要返回的字段
            String includes[] = CommonUtil.includesOrExcludes(INCLUDES);
            //不需要返回的字段
            String excludes[] = CommonUtil.includesOrExcludes(EXCLUDES);

            //调用搜索引擎
            SearchResult searchResult = searchFamousComponent.searchFamousInfo(fields,includes,excludes,0,0);
            List<Map<String,Object>> documents =searchResult.getDocuments();
            List<FamousPortrait> portraitList= map2Bean(documents);

            if(null!=portraitList && !portraitList.isEmpty()){
                result.setBeans(portraitList);
                result.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
                result.setReturnMessage("查询成功");
            }
        }catch(Exception e){
            logger.error(e.getMessage(),e);
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
            //点击图片查询作者详情使用：famousId
            String famousId = String.valueOf(document.get("famousId"));
            String portraitName = String.valueOf(document.get("portraitName"));
            String chineseName= String.valueOf(document.get("chineseName"));
            String englishName= String.valueOf(document.get("englishName"));
            String achievement= String.valueOf(document.get("achievement"));
            String birthYear= String.valueOf(document.get("birthYear"));
            String summaryInfo= String.valueOf(document.get("summaryInfo"));

            FamousPortrait famousPortrait = new FamousPortrait(Long.valueOf(famousId),portraitName,chineseName,englishName,achievement,birthYear,summaryInfo);
            famousList.add(famousPortrait);
        }
        return famousList;
    }
}
