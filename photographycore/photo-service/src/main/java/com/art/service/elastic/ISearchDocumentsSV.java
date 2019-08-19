package com.art.service.elastic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ISearchDocumentsSV {

    /**
     * 根据索引名称，索引类型，索引ID 查询文档
     * @param indexName
     * @param indexType
     * @param id
     */
    String searching(String indexName,String indexType,String id);


    /**
     * 根据索引名称，索引类型，字段名称 查询文档
     * @param indexName
     * @param indexType
     * @param termFields
     * @return
     */
    List<Map<String,Object>> searching(String indexName, String indexType, Map<String,Object> termFields);


    /**
     * 新增或更新文档
     * @param indexName
     * @param indexType
     * @param id
     * @throws IOException
     */
    void createOrUpdating(String indexName, String indexType, String id, Map<String,Object> document) throws IOException;


    /**
     * 删除文档
     * @param indexName
     * @param indexType
     * @param id
     */
    void deleting(String indexName,String indexType,String id);

}
