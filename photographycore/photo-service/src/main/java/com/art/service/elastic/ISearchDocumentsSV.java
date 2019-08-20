package com.art.service.elastic;

import com.art.beans.elastic.SearchResult;
import java.io.IOException;
import java.util.Map;

public interface ISearchDocumentsSV {

    /**
     * 根据索引名称，索引类型，索引ID 查询文档
     * @param indexName
     * @param indexType
     * @param id
     */
    SearchResult searching(String indexName,String indexType,String id);


    /**
     * 根据索引名称，索引类型，字段名称 查询文档
     * @param indexName
     * @param indexType
     * @param termFields
     * @return
     */
    SearchResult searching(String indexName, String indexType, Map<String,Object> termFields);


    /**
     * 新增或更新文档
     * @param indexName
     * @param indexType
     * @param id
     * @throws IOException
     */
    SearchResult createOrUpdating(String indexName, String indexType, String id, Map<String,Object> document) throws IOException;


    /**
     * 删除文档
     * @param indexName
     * @param indexType
     * @param id
     */
    SearchResult deleting(String indexName,String indexType,String id);

}
