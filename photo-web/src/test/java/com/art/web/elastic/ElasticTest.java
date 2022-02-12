package com.art.web.elastic;

import com.art.elastic.vo.SearchResult;
import com.art.elastic.WebApplication;
import com.art.elastic.web.component.elastic.SearchComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 搜索引擎底层方法测试类
 *
 * @author wslixiaoliang
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class ElasticTest {

    @Autowired(required = false)
    private SearchComponent searchDocumentsComponent;

    @Test
    public void testSearch() throws IOException {
        Map<String, Object> document = new HashMap<>();
//        document.put("keywordId","100");
        document.put("chineseName", "阿尔伯特");
//        document.put("landingTime","2999-10-11");

//        SearchResult createResult = searchDocumentsComponent.createOrUpdating("photo.search_keyword","search_keyword","100",document);//新增/更新
//        SearchResult searchIdResult = searchDocumentsComponent.searching("photo.search_keyword","search_keyword","100");//主键查询
        SearchResult searchResult = null;//条件查询
        try {
//            searchResult = searchDocumentsComponent.searching("world.famous_production","famous_production",document);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        SearchResult deleteResult = searchDocumentsComponent.deleting("photo.search_keyword","search_keyword","100");//删除文档

//        System.out.println("新增结果：======================"+createResult);
//        System.out.println("主键查询结果：======================"+searchIdResult);
        logger.info("字段查询结果：======================" + searchResult.getDocuments());
//        System.out.println("删除结果：======================"+deleteResult);

//        LOGGER.info("新增结果：======================"+createResult);
//        LOGGER.info("主键查询结果：======================"+searchIdResult);
        logger.info("字段查询结果：======================" + searchResult);
//        LOGGER.info("删除结果：======================"+deleteResult);

    }

    @Test
    public void getRoudam() {
        long pnc = 0;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    if (i != j && i != k && j != k) {
                        pnc++;
                        StringBuffer num = new StringBuffer();
                        num.append(i);
                        num.append(j);
                        num.append(k);
                        num.append(" ");
                        System.out.print(num);
                    }
                }
            }
        }
        System.out.println("\n" + "随机组合数的个数为： " + pnc);
    }

    /**
     * 时间格式化-long型 转化成 String类型 （切记long型后面要加L）
     */
    @Test
    public void testLong2StringTime() {
        long time  = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        System.out.println("==============现在的时间为："+nowTime);
    }
}
