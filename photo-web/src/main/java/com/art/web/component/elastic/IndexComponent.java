/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.web.component.elastic;

import com.art.elastic.util.SearchConstans;
import com.art.elastic.util.StringUtil;
import com.art.elastic.vo.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 搜索引擎 索引基类
 * @author wslixiaoliang
 */
@Component
@Slf4j
public class IndexComponent {
    /**
     * 指定文档Id:新增或更新文档
     * @param indexName
     * @param indexType
     * @param id
     * @throws IOException
     * 注：如果文档ID存在默认更新文档，若不存在则新增
     */
    public SearchResult createOrUpdating(String indexName, String indexType, String id, Map<String,Object> document) throws IOException
    {
        String status="";
        SearchResult searchResult = new SearchResult();
        try{
            IndexResponse response = EngineClient.getConnection()
                    .prepareIndex(indexName,indexType,id)
                    .setTimeout(new TimeValue(60, TimeUnit.SECONDS))
                    .setSource(document).get();
            if(StringUtil.isNotEmpty(String.valueOf(response.status()))){
                status = String.valueOf(response.status());
            }
            searchResult.setStatus(status);
            searchResult.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
            searchResult.setReturnMsg("文档新增成功");
            log.info("文档新增成功");
        }catch(Exception e){
            log.error("文档新增失败: {}",e.getMessage());
            status = SearchConstans.CREATE_FAILURE_STATUS;
            searchResult.setStatus(status);
            searchResult.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
            searchResult.setReturnMsg("文档新增失败");
        }
        return searchResult;
    }





}
