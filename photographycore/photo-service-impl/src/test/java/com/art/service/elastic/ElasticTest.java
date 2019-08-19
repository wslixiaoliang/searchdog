package com.art.service.elastic;

import com.art.ServiceApplication;
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
//        document.put("keywordId","100");
        document.put("keywordName","金星移民计划");
//        document.put("landingTime","2999-10-11");
        List<Map<String,Object>> response = searchDocumentsSV.searching("photo.search_keyword","search_keyword",document);

//        searchDocumentsSV.createOrUpdating("photo.search_keyword","search_keyword","100",document);
//        String response = searchDocumentsSV.searching("photo.search_keyword","search_keyword","100");
        System.out.println("查询结果如下=================="+response);
        LOGGER.debug("查询结果如下"+response);
//        searchDocumentsSV.deleting("photo.search_keyword","search_keyword","KlXlGWwBm_lpTzijdMGf");
    }
}
