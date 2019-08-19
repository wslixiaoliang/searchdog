package com.art.service.elastic;

import com.alibaba.dubbo.config.annotation.Service;
import com.art.util.famous.LiangUtil;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 搜索引擎 查询基类
 */
@Service
public class SearchDocumentsImpl implements ISearchDocumentsSV
{

    /**
     * 根据：索引名称，索引类型，索引ID 查询文档
     * @param indexName
     * @param indexType
     * @param id
     */
    @Override
    public String searching(String indexName,String indexType,String id)
    {
        GetResponse response =null;

        if(LiangUtil.isNotEmpty(indexName)&& LiangUtil.isNotEmpty(indexType)&&LiangUtil.isNotEmpty(id))
        {
            response = EngineClient.getConnection().prepareGet(indexName,indexType,id).execute().actionGet();
        }
        return response.getSourceAsString();
    }


    /**
     * 根据：索引名称，索引类型，指定字段 查询文档
     * @param indexName
     * @param indexType
     * @param termFields
     * @return
     */
    public List<Map<String,Object>> searching(String indexName, String indexType, Map<String,Object> termFields) {
        SearchResponse response;

        List<Map<String, Object>> responses = new ArrayList<>();

        if (null != termFields && termFields.size() > 0) {

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
                            .setTimeout(new TimeValue(30, TimeUnit.SECONDS))
                            .execute()
                            .actionGet();
                    SearchHits searchHits = response.getHits();
                    searchHits.getTotalHits();
                    SearchHit[] hits = searchHits.getHits();
                    Map<String, Object> resultMsg = new HashMap<>();
                    resultMsg.put("total", hits.length);
                    responses.add(resultMsg);

                    //处理查询结果（循环放入listMap）
                    for (SearchHit searchHit : hits) {
                        Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                        responses.add(sourceAsMap);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        return responses;
    }
    /**
     * 新增或更新文档
     * @param indexName
     * @param indexType
     * @param id
     * @throws IOException
     * 注：如果文档ID存在默认更新文档，若不错在则新增
     */
    @Override
    public void createOrUpdating(String indexName, String indexType, String id, Map<String,Object> document) throws IOException
    {
        IndexResponse response = EngineClient.getConnection()
                                .prepareIndex(indexName,indexType,id)
                                .setSource(document).get();
    }

    /**
     * 删除文档
     * @param indexName
     * @param indexType
     * @param id
     */
    @Override
    public void deleting(String indexName,String indexType,String id)
    {

        DeleteResponse response = EngineClient.getConnection().prepareDelete(indexName,indexType,id).get();

    }




}
