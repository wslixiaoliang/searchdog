/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.elastic.web.component.elastic;

import com.art.elastic.util.SearchConstans;
import com.art.elastic.util.StringUtil;
import com.art.elastic.vo.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * 搜索引擎，删除基类
 * @author wslixiaoliang
 */
@Component
@Slf4j
public class DeleteComponent {

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
            searchResult.setReturnMsg("文档删除成功");
            logger.info("文档删除成功……");
        }catch(Exception e){
            logger.error("文档删除失败: {}",e.getMessage());
            searchResult.setStatus(SearchConstans.DELETE_FAILURE_STATUS);
            searchResult.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
            searchResult.setReturnMsg("文档删除失败");
        }
        return searchResult;
    }

}
