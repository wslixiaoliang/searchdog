package com.art.service.elastic;

import com.art.ServiceApplication;
import com.art.beans.elastic.SearchResult;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 搜索引擎底层方法测试类
 * @author wslixiaoliang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class ElasticTest {

    @Autowired(required = false)
    private ISearchDocumentsSV searchDocumentsSV;
    private static final Logger LOGGER = Logger.getLogger(ElasticTest.class);

    @Test
    public void testSearch() throws IOException
    {
        Map<String,Object> document = new HashMap<>();
        document.put("keywordId","100");
        document.put("keywordName","金星移民计划");
        document.put("landingTime","2999-10-11");

        SearchResult createResult = searchDocumentsSV.createOrUpdating("photo.search_keyword","search_keyword","100",document);//新增/更新
        SearchResult searchIdResult = searchDocumentsSV.searching("photo.search_keyword","search_keyword","100");//主键查询
        SearchResult searchResult = searchDocumentsSV.searching("photo.search_keyword","search_keyword",document);//条件查询
        SearchResult deleteResult = searchDocumentsSV.deleting("photo.search_keyword","search_keyword","100");//删除文档

        System.out.println("新增结果：======================"+createResult);
        System.out.println("主键查询结果：======================"+searchIdResult);
        System.out.println("字段查询结果：======================"+searchResult);
        System.out.println("删除结果：======================"+deleteResult);

        LOGGER.info("新增结果：======================"+createResult);
        LOGGER.info("主键查询结果：======================"+searchIdResult);
        LOGGER.info("字段查询结果：======================"+searchResult);
        LOGGER.info("删除结果：======================"+deleteResult);

    }
}
