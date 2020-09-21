/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.web.component.elastic;

import com.art.beans.elastic.SearchResult;
import com.art.util.SearchConstans;
import com.art.util.StringUtil;
import org.apache.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 搜索引擎，删除基类
 * @author wslixiaoliang
 */
@Component
public class DeleteComponent {

    private static final Logger LOGGER = Logger.getLogger(DeleteComponent.class);

    /**
     * 删除文档
     * @param indexName
     * @param indexType
     * @param id
     */
    public SearchResult deleting(String indexName, String indexType, String id)
    {
        String status="";
        SearchResult searchResult = new SearchResult();
        try{
            DeleteResponse response = EngineClient.getConnection()
                    .prepareDelete(indexName,indexType,id)
                    .setTimeout(new TimeValue(60, TimeUnit.SECONDS))
                    .get();
            if(StringUtil.isNotEmpty(String.valueOf(response.status()))){
                status = String.valueOf(response.status());
            }
            searchResult.setStatus(status);
            searchResult.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
            searchResult.setReturnMsg("删除成功……");
        }catch(Exception e){
            LOGGER.error(e);
            e.printStackTrace();
            searchResult.setStatus(SearchConstans.DELETE_FAILURE_STATUS);
            searchResult.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
            searchResult.setReturnMsg("删除失败……");
        }
        return searchResult;
    }

}
