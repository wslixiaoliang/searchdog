package com.art.web.component.elastic;

import com.art.beans.elastic.SearchResult;
import com.art.util.SearchConstans;
import com.art.util.StringUtil;
import org.apache.log4j.Logger;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Component;

import javax.swing.text.Highlighter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 搜索引擎，搜索基类
 * @author wslixiaoliang
 */
@Component
public class SearchComponent {

    private static final Logger LOGGER = Logger.getLogger(SearchComponent.class);
    private HighlightBuilder.Field highlightBuilder;

    /**
     * 搜索引擎：统一查询接口
     * @param params 封装基本参数 例如：索引名称，索引类型，索引文档ID
     * @param termFields 精确匹配字段
     * @param page 分页参数（当前页数）
     * @param limit 每页数据条数
     * @return
     */
    public SearchResult searching(Map<String,Object> params, Map<String,Object> termFields,int page,int limit)throws Exception
    {
        SearchResult searchResult = new SearchResult();

        String indexName = String.valueOf(params.get("indexName"));
        String indexType = String.valueOf(params.get("indexType"));
        String docId = String.valueOf(params.get("docId"));
        if(StringUtil.isEmpty(indexName) || StringUtil.isEmpty(indexType)){
            return searchResult;
        }
        if(StringUtil.isNotEmpty(docId)){
            searchResult = searching(indexName,indexType,docId);
        }
        if(null!=termFields && termFields.size()>0){
           if(0==page && 0==limit){
               searchResult = searching(indexName,indexType,termFields);
           }else{
               searchResult = searching(indexName,indexType,termFields,page,limit);
           }
        }
        if(termFields.size()==0){
            if(0==page && 0==limit){
                searchResult = searching(indexName,indexType);
            }else{

                searchResult = searching(indexName,indexType,page,limit);
            }
        }
        return searchResult;

    }



    /**
     * 精确匹配查询：根据文档Id查询
     * @param indexName
     * @param indexType
     * @param docId
     */

    public SearchResult searching(String indexName, String indexType, String docId) throws Exception{

        SearchResult searchResult = new SearchResult();
        GetResponse response =null;
        Map<String,Object> document;
        List<Map<String,Object>> list = new ArrayList<>();

        try{
            if(StringUtil.isNotEmpty(indexName)&& StringUtil.isNotEmpty(indexType)&&StringUtil.isNotEmpty(docId)) {


                response = EngineClient.getConnection().prepareGet(indexName,indexType,docId).execute().actionGet();
            }
            document = response.getSourceAsMap();
            if(null== document ||document.isEmpty()){
                return searchResult;
            }
            list.add(document);
            searchResult.setDocuments(list);
            searchResult.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
            searchResult.setReturnMsg("查询成功……");

        }catch(Exception e){
            LOGGER.info(e);
            searchResult.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
            searchResult.setReturnMsg("查询失败……");
        }
        return searchResult;
    }

    /**
     * 精确匹配查询：指定字段
     * @param indexName
     * @param indexType
     * @param termFields
     * @return
     */
    public SearchResult searching(String indexName, String indexType, Map<String,Object> termFields)throws Exception
    {
        SearchResult searchResult = new SearchResult();
        List<Map<String, Object>> documents = new ArrayList<>();

        //初始化：搜索对象
        SearchRequestBuilder searchRequestBuilder = SearchrequestFactory.build(indexName,indexType);
        SearchResponse searchResponse;
        if (null != termFields && termFields.size() > 0)
        {
            try {
                for (Map.Entry<String, Object> entry : termFields.entrySet()) {
                    String fieldName = entry.getKey();
                    String fieldValue = String.valueOf(entry.getValue());

                    //执行搜索
                    searchResponse = searchRequestBuilder
                            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)//查询类型为：精确查询
                            .setQuery(QueryBuilders.matchQuery(fieldName, fieldValue))//设置查询字段
                            .setFrom(0)//设置查询数据的起始位置
                            .setSize(20)//设置返回数据的最大条数
                            .setExplain(true)// 设置是否按查询匹配度排序
                            .setTimeout(new TimeValue(60, TimeUnit.SECONDS))
                            .execute()
                            .actionGet();

                    //解析返回结果
                    SearchHits searchHits = searchResponse.getHits();
                    searchHits.getTotalHits();
                    SearchHit[] hits = searchHits.getHits();
                    searchResult.setTotalCount(hits.length);

                    //处理查询结果（循环放入listMap）
                    for (SearchHit searchHit : hits) {
                        Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                        documents.add(sourceAsMap);
                    }
                }
                searchResult.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
                searchResult.setReturnMsg("查询成功……");
            } catch (Exception e) {
                LOGGER.error(e.getMessage(),e);
                searchResult.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
                searchResult.setReturnMsg("查询失败……");
            }
        }
        searchResult.setDocuments(documents);
        return searchResult;
    }

    /**
     * 精确匹配查询：指定字段分页查询
     * @param indexName
     * @param indexType
     * @param termFields
     * @return
     * 注：setFetchSource()，必选返回字段，忽略字段；addStoredField() 排序字段
     *
     */
    public SearchResult searching(String indexName, String indexType, Map<String,Object> termFields,int page,int limit)throws Exception
    {
        SearchResult searchResult = new SearchResult();
        List<Map<String, Object>> documents = new ArrayList<>();
        SearchResponse searchResponse;
        if (null != termFields && termFields.size() > 0)
        {
            try {
                    //初始化：查询对象
                    SearchRequestBuilder searchRequestBuilder = SearchrequestFactory.build(indexName,indexType);

                    long totalHits = searchRequestBuilder.setQuery(QueryBuilders.matchAllQuery()).get().getHits().getTotalHits();//总条数
                    final int  start = (page-1)*limit;

                    //组装查询条件
                    BoolQueryBuilder builder = getQueryBuder(termFields);
                    //配置高亮属性
                    HighlightBuilder highlightBuilder = getHightBuilder(termFields);


                    searchRequestBuilder
                            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)//查询类型为：精确查询
                            .setQuery(builder)//设置查询字段
                            .setFrom(start)//设置分页参数
                            .setSize(limit)
                            .highlighter(highlightBuilder)//设置高亮
                            .setExplain(true)// 设置是否按查询匹配度排序
                            .setTimeout(new TimeValue(60, TimeUnit.SECONDS));

                    //执行搜索
                    searchResponse = searchRequestBuilder.get();

                    //搜索结果解析
                    SearchHits searchHits = searchResponse.getHits();
                    searchHits.getTotalHits();
                    SearchHit[] hits = searchHits.getHits();
                    searchResult.setTotalCount(Integer.parseInt(String.valueOf(totalHits)));
                    Map<String, Object> source;
                    //处理查询结果（循环放入listMap）
                    for (SearchHit searchHit : hits) {

                        //获取高亮字段
                        Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                        HighlightField nameField = highlightFields.get("chineseName");
                        HighlightField contentField = highlightFields.get("productionName");
                        HighlightField titleField = highlightFields.get("summaryInfo");

                        source = searchHit.getSourceAsMap();

                        //高亮替换
                        putHightConten(nameField,source,"chineseName");
                        putHightConten(titleField,source,"summaryInfo");
                        putHightConten(contentField,source,"productionName");

                        documents.add(source);
                    }
                searchResult.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
                searchResult.setReturnMsg("查询成功……");
            } catch (Exception e) {
                LOGGER.error(e.getMessage(),e);
                searchResult.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
                searchResult.setReturnMsg("查询失败……");
            }
        }
        searchResult.setDocuments(documents);
        return searchResult;
    }

    /**
     * 组装查询条件
     * @param termFields
     * @return
     */
    private BoolQueryBuilder getQueryBuder(Map<String,Object> termFields){

        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        if(null==termFields ||termFields.size()==0){
            return builder;
        }
        for(Map.Entry<String,Object> entry:termFields.entrySet()){
            String fieldName = entry.getKey();
            String fieldValue = String.valueOf(entry.getValue());
            builder.should(QueryBuilders.termQuery(fieldName,fieldValue));
        }
        return builder;

    }

    /**
     * 组装高亮查询器
     * @param termFields
     * @return
     */
    private HighlightBuilder getHightBuilder(Map<String,Object> termFields){

        HighlightBuilder highlightBuilder = new HighlightBuilder(); //生成高亮查询器
        if(null==termFields ||termFields.size()==0){
            return highlightBuilder;
        }

        for(Map.Entry<String,Object> entry:termFields.entrySet()){
            String fieldName = entry.getKey();
            highlightBuilder.field(fieldName); //高亮查询字段
        }

        highlightBuilder.requireFieldMatch(false);//如果要多个字段高亮,这项要为false
        highlightBuilder.preTags("<span style=\"color:red\">");//高亮设置
        highlightBuilder.postTags("</span>");
        //下面这两项,如果你要高亮如文字内容等有很多字的字段,必须配置,不然会导致高亮不全,文章内容缺失等
        highlightBuilder.fragmentSize(800000); //最大高亮分片数
        highlightBuilder.numOfFragments(0); //从第一个分片获取高亮片段
        return highlightBuilder;
    }

    /**
     * 高亮内容替换
     * @param contentField
     * @param source
     */
    private void putHightConten(HighlightField contentField,Map<String, Object> source,String key){

        if(contentField!=null){
            Text[] fragments = contentField.fragments();
            String name = "";
            for (Text text : fragments) {
                name+=text;
            }
            source.put(key, name);//高亮字段替换掉原本的内容
        }
    }




    /**
     * 全量查询：指定返回文档数量
     * @param indexName
     * @param indexType
     */
    public SearchResult searching(String indexName,String indexType)
    {
        SearchResult searchResult = new SearchResult();
        List<Map<String,Object>> documents = new ArrayList<>();
        try{
            //初始化：搜索对象
            SearchRequestBuilder searchRequestBuilder = SearchrequestFactory.build(indexName,indexType);

            //执行搜索
            SearchResponse searchResponse = searchRequestBuilder.setSize(20).setQuery(QueryBuilders.matchAllQuery()).execute().actionGet(); // 查询所有

            SearchHits searchHits=searchResponse.getHits();
            SearchHit[] hits = searchHits.getHits();
            searchResult.setTotalCount(hits.length);

            if(hits.length >0){
                for (SearchHit searchHit : hits) {
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    documents.add(sourceAsMap);
                }
            }
            searchResult.setReturnCode(SearchConstans.SUCESSS_RETURN_CODE);
            searchResult.setReturnMsg("查询成功……");
        }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
            searchResult.setReturnCode(SearchConstans.FAILURE_RETURN_CODE);
            searchResult.setReturnMsg("查询失败……");
        }
        searchResult.setDocuments(documents);
        return searchResult;
    }

    /**
     * 全量查询：分页查询
     * @param indexName
     * @param indexType
     * @param page
     * @param limit
     * @return
     */
    public SearchResult searching(String indexName,String indexType ,int page,int limit) throws Exception
    {
        SearchResult searchResult = new SearchResult();
        List<Map<String,Object>> documents = new ArrayList<>();

        //初始化：搜索对象
        SearchRequestBuilder searchRequestBuilder = SearchrequestFactory.build(indexName,indexType);

        //查询文档总数量
        long totalHits = searchRequestBuilder
                .setQuery(QueryBuilders.matchAllQuery())
                .get().getHits()
                .getTotalHits();//总条数
        final int  start = (page-1)*limit;

        //执行：分页查询操作
        SearchResponse searchResponse = searchRequestBuilder
                .setFrom(start)
                .setSize(limit)
                .get();
        SearchHits searchHits = searchResponse.getHits();
        SearchHit[] hits = searchHits.getHits();
        searchResult.setTotalCount(Integer.parseInt(String.valueOf(totalHits)));

        if(hits.length >0){
            for (SearchHit searchHit : hits) {
                Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                documents.add(sourceAsMap);
            }
        }
        searchResult.setDocuments(documents);
        return searchResult;

    }

    /**
     * 静态内部类：构造搜索对象 SearchRequestBuilder
     */
    public static class SearchrequestFactory{

            public static SearchRequestBuilder build(String indexName, String indexType) throws Exception{

                return EngineClient.getConnection().prepareSearch(indexName).setTypes(indexType);
            }
    }



}
