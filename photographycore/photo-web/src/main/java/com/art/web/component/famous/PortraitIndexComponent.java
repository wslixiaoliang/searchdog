package com.art.web.component.famous;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.elastic.SearchResult;
import com.art.beans.famous.FamousPortrait;
import com.art.service.famous.IFamousPortraitSV;
import com.art.util.Constans;
import com.art.util.LiangUtil;
import com.art.web.component.elastic.IndexComponent;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PortraitIndexComponent {

    @Autowired
    private IndexComponent searchDocumentsComponent;
    @Reference
    private IFamousPortraitSV famousPortraitSV;
    private static Logger LOGGER  = Logger.getLogger(PortraitIndexComponent.class);
    private static int count = 0;

    public SearchResult portraitIndex(List<String> famousIds)
    {
        SearchResult searchResult = new SearchResult();
        List<FamousPortrait> portraitList = getFamous(famousIds);//查询portraitList
        List<Map<String,Object>> documents = cfgIndexColumn(portraitList);//组装索引字段

        if(null== documents || documents.isEmpty()){
            return searchResult;
        }
        for( Map<String,Object> document:documents){
            if(null!=document && document.containsKey(Constans.Portrait.FAMOUS_ID)){
                String famousId = String.valueOf(document.get(Constans.Portrait.FAMOUS_ID));
                try {
                    searchResult = searchDocumentsComponent.createOrUpdating(Constans.Portrait.INDEX_NAME,Constans.Portrait.INDEX_TYPE,famousId,document);
                    if(Constans.SUCESSS_RETURN_CODE.equals(String.valueOf(searchResult.getReturnCode()))){
                        count++;
                        searchResult.setTotalCount(count);
                    }
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
                famousPortraitList = famousPortraitSV.getfamousListByIds(famousMap);
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
            document.put(Constans.Portrait.PORTRAIT_ID, LiangUtil.isNotEmpty(String.valueOf(portrait.getPortraitId()))?String.valueOf(portrait.getPortraitId()):"");
            document.put(Constans.Portrait.PORTRAIT_NAME,LiangUtil.isNotEmpty(String.valueOf(portrait.getPortraitName()))?String.valueOf(portrait.getPortraitName()):"");
            document.put(Constans.Portrait.FAMOUS_ID,LiangUtil.isNotEmpty(String.valueOf(portrait.getFamousId()))?String.valueOf(portrait.getFamousId()):"");
            document.put(Constans.Portrait.CHINESE_NAME,LiangUtil.isNotEmpty(String.valueOf(portrait.getChineseName()))?String.valueOf(portrait.getChineseName()):"");
            document.put(Constans.Portrait.RELATIVE_LOCATION,LiangUtil.isNotEmpty(String.valueOf(portrait.getRelativeLocation()))?String.valueOf(portrait.getRelativeLocation()):"");
            documents.add(document);
        }
        return documents;
    }

}
