package com.art.web.component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.elastic.SearchResult;
import com.art.beans.famous.FamousPortrait;
import com.art.service.elastic.ISearchDocumentsSV;
import com.art.service.famous.IFamousPortraitSV;
import com.art.util.famous.Constans;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FamousIndexComponent {

    @Reference
    private ISearchDocumentsSV searchDocumentsSV;
    @Reference
    private IFamousPortraitSV sv;
    private static Logger LOGGER  = Logger.getLogger(FamousIndexComponent.class);

    public SearchResult portraitIndex(List<String> famousIds)
    {
        SearchResult searchResult = new SearchResult();
        List<FamousPortrait> portraitList = getFamous(famousIds);//查询portraitList
        List<Map<String,Object>> documents = cfgIndexColumn(portraitList);//组装索引字段

        if(null== documents || documents.isEmpty()){
            return searchResult;
        }
        for( Map<String,Object> document:documents){
            if(null!=document && document.containsKey(Constans.FAMOUS_ID)){
                String famousId = String.valueOf(document.get(Constans.FAMOUS_ID));
                try{
                    searchResult = searchDocumentsSV.createOrUpdating(Constans.INDEX_NAME,Constans.INDEX_TYPE,famousId,document);
                }catch(IOException e){
                    LOGGER.error("新增索引失败：IO异常"+e);
                }
            }
        }
        return searchResult;
    }

    /**
     * 根据多Id 查询famous对象
     * @param famousIds
     * @return famousList
     */
    private List<FamousPortrait> getFamous(List<String> famousIds)
    {
        List<FamousPortrait> famousPortraitList = new ArrayList<>();
        Map<String,Object> famousMap = new HashMap<>();
        try{
            if(null!=famousIds && !famousIds.isEmpty()) {
                famousMap.put("famousList",famousIds);
                famousPortraitList = sv.getfamousListByIds(famousMap);
            }
        }catch(Exception e){
            LOGGER.error("查询失败"+e);
        }
        return famousPortraitList;
    }

    /**
     * 组装索引字段
     * @param famousPortraitList
     * @return
     */
    private List<Map<String,Object>> cfgIndexColumn(List<FamousPortrait> famousPortraitList){
        List<Map<String,Object>> documents = new ArrayList<>();
        if(null==famousPortraitList ||famousPortraitList.isEmpty()){
            return documents;
        }
        for(FamousPortrait portrait :famousPortraitList){
            Map<String,Object> document = new HashMap<>();
            document.put(Constans.PORTRAIT_ID,String.valueOf(portrait.getPortraitId()));
            document.put(Constans.PORTRAIT_NAME,String.valueOf(portrait.getPortraitName()));
            document.put(Constans.FAMOUS_ID,String.valueOf(portrait.getFamousId()));
            document.put(Constans.CHINESE_NAME,String.valueOf(portrait.getChineseName()));
            document.put(Constans.RELATIVE_LOCATION,String.valueOf(portrait.getRelativeLocation()));
            documents.add(document);
        }
        return documents;
    }

}
