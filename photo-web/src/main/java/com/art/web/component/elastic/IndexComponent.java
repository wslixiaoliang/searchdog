package com.art.web.component.elastic;

import com.art.beans.elastic.SearchResult;
import com.art.util.SearchConstans;
import com.art.util.StringUtil;
import org.apache.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 搜索引擎 索引基类
 */

@Component
public class IndexComponent
{

    private static final Logger LOGGER = Logger.getLogger(IndexComponent.class);
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
            searchResult.setReturnMsg("新增成功……");
        }catch(Exception e){
            LOGGER.error(e);
            status = SearchConstans.CREATE_FAILURE_STATUS;
            searchResult.setStatus(status);
            searchResult.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
            searchResult.setReturnMsg("新增失败……");
        }
        return searchResult;
    }





}
