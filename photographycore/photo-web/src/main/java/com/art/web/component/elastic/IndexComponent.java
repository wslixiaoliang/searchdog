package com.art.web.component.elastic;

import com.art.beans.elastic.SearchResult;
import com.art.util.famous.Constans;
import com.art.util.famous.LiangUtil;
import org.apache.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 搜索引擎 查询基类
 */
@Component
public class IndexComponent
{
    private static Logger LOGGER = Logger.getLogger(IndexComponent.class);

    /**
     * 根据：索引名称，索引类型，索引ID 查询文档
     * @param indexName
     * @param indexType
     * @param id
     */

    public SearchResult searching(String indexName,String indexType,String id)
    {
        SearchResult searchResult = new SearchResult();
        GetResponse response =null;
        Map<String,Object> document;
        List<Map<String,Object>> list = new ArrayList<>();
        try{
            if(LiangUtil.isNotEmpty(indexName)&& LiangUtil.isNotEmpty(indexType)&&LiangUtil.isNotEmpty(id))
            {
                response = EngineClient.getConnection().prepareGet(indexName,indexType,id).execute().actionGet();
            }
            document = response.getSourceAsMap();
            if(null== document ||document.isEmpty()){
                return searchResult;
            }
            list.add(document);
            searchResult.setDocuments(list);
            searchResult.setReturnCode(Constans.SUCESSS_RETURN_CODE);
            searchResult.setReturnMsg("查询成功……");

        }catch(Exception e){
            LOGGER.info(e);
            searchResult.setReturnCode(Constans.FAILURE_RETURN_CODE);
            searchResult.setReturnMsg("查询失败……");
        }
        return searchResult;
    }

    /**
     * 根据：索引名称，索引类型，指定字段 查询文档
     * @param indexName
     * @param indexType
     * @param termFields
     * @return
     */
    public SearchResult searching(String indexName, String indexType, Map<String,Object> termFields)
    {
        SearchResult searchResult = new SearchResult();
        List<Map<String, Object>> documents = new ArrayList<>();
        SearchResponse response;
        if (null != termFields && termFields.size() > 0)
        {
            try {
                for (Map.Entry<String, Object> entry : termFields.entrySet()) {
                    String fieldName = entry.getKey();
                    String fieldValue = String.valueOf(entry.getValue());
                    response = EngineClient.getConnection().prepareSearch(indexName)
                            .setTypes(indexType)
                            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)//查询类型为：精确查询
                            .setQuery(QueryBuilders.matchQuery(fieldName, fieldValue))//设置查询字段
                            .setFrom(0)//设置查询数据的起始位置
                            .setSize(10)//设置返回数据的最大条数
                            .setExplain(true)// 设置是否按查询匹配度排序
                            .setTimeout(new TimeValue(60, TimeUnit.SECONDS))
                            .execute()
                            .actionGet();
                    SearchHits searchHits = response.getHits();
                    searchHits.getTotalHits();
                    SearchHit[] hits = searchHits.getHits();
                    searchResult.setTotalCount(hits.length);

                    //处理查询结果（循环放入listMap）
                    for (SearchHit searchHit : hits) {
                        Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                        documents.add(sourceAsMap);
                    }
                }
                searchResult.setReturnCode(Constans.SUCESSS_RETURN_CODE);
                searchResult.setReturnMsg("查询成功……");
            } catch (Exception e) {
                LOGGER.error(e);
                searchResult.setReturnCode(Constans.FAILURE_RETURN_CODE);
                searchResult.setReturnMsg("查询失败……");
            }
        }
        searchResult.setDocuments(documents);
        return searchResult;
    }
    /**
     * 新增或更新文档
     * @param indexName
     * @param indexType
     * @param id
     * @throws IOException
     * 注：如果文档ID存在默认更新文档，若不错在则新增
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
            if(LiangUtil.isNotEmpty(String.valueOf(response.status()))){
                status = String.valueOf(response.status());
            }
            searchResult.setStatus(status);
            searchResult.setReturnCode(Constans.SUCESSS_RETURN_CODE);
            searchResult.setReturnMsg("新增成功……");
        }catch(Exception e){
            LOGGER.error(e);
            status = Constans.CREATE_FAILURE_STATUS;
            searchResult.setStatus(status);
            searchResult.setReturnCode(Constans.FAILURE_RETURN_CODE);
            searchResult.setReturnMsg("新增失败……");
        }
        return searchResult;
    }

    /**
     * 删除文档
     * @param indexName
     * @param indexType
     * @param id
     */
    public SearchResult deleting(String indexName,String indexType,String id)
    {
        String status="";
        SearchResult searchResult = new SearchResult();
        try{
            DeleteResponse response = EngineClient.getConnection()
                    .prepareDelete(indexName,indexType,id)
                    .setTimeout(new TimeValue(60,TimeUnit.SECONDS))
                    .get();
            if(LiangUtil.isNotEmpty(String.valueOf(response.status()))){
                status = String.valueOf(response.status());
            }
            searchResult.setStatus(status);
            searchResult.setReturnCode(Constans.SUCESSS_RETURN_CODE);
            searchResult.setReturnMsg("删除成功……");
        }catch(Exception e){
            LOGGER.error(e);
            e.printStackTrace();
            searchResult.setStatus(Constans.DELETE_FAILURE_STATUS);
            searchResult.setReturnCode(Constans.FAILURE_RETURN_CODE);
            searchResult.setReturnMsg("删除失败……");
        }
        return searchResult;
    }




}
