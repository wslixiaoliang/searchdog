package com.art.web.elastic;

import com.art.beans.elastic.SearchResult;
import com.art.web.WebApplication;
import com.art.web.component.elastic.IndexComponent;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 搜索引擎底层方法测试类
 * @author wslixiaoliang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class ElasticTest {

    @Autowired(required = false)
    private IndexComponent searchDocumentsComponent;
    private static final Logger LOGGER = Logger.getLogger(ElasticTest.class);

    @Test
    public void testSearch() throws IOException
    {
        Map<String, Object> document = new HashMap<>();
//        document.put("keywordId","100");
        document.put("chineseName","阿尔伯特");
//        document.put("landingTime","2999-10-11");

//        SearchResult createResult = searchDocumentsComponent.createOrUpdating("photo.search_keyword","search_keyword","100",document);//新增/更新
//        SearchResult searchIdResult = searchDocumentsComponent.searching("photo.search_keyword","search_keyword","100");//主键查询
        SearchResult searchResult = searchDocumentsComponent.searching("world.famous_production","famous_production",document);//条件查询
//        SearchResult deleteResult = searchDocumentsComponent.deleting("photo.search_keyword","search_keyword","100");//删除文档

//        System.out.println("新增结果：======================"+createResult);
//        System.out.println("主键查询结果：======================"+searchIdResult);
        System.out.println("字段查询结果：======================"+searchResult.getDocuments());
//        System.out.println("删除结果：======================"+deleteResult);

//        LOGGER.info("新增结果：======================"+createResult);
//        LOGGER.info("主键查询结果：======================"+searchIdResult);
        LOGGER.info("字段查询结果：======================"+searchResult);
//        LOGGER.info("删除结果：======================"+deleteResult);

    }
}
