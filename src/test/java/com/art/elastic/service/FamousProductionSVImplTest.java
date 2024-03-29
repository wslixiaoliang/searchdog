package com.art.elastic.service;

import com.art.elastic.SearchDogApplication;
import com.art.elastic.vo.FamousProduction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 世界名人肖像SV测试类
 * @author wslixiaoliang
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchDogApplication.class)
public class FamousProductionSVImplTest {

    @Autowired(required = false)
    private IFamousProductionSV productionSV;

    @Test
    public void testGetProductionInfos(){
        long famousId = 1;
        String productionName = "相对";
        Map<String,Object> map = new HashMap<>();
        map.put("famousId",famousId);
        map.put("productionName",productionName);
        List<FamousProduction> productionList = productionSV.getProductionInfos(map);
        if(null!=productionList && !productionList.isEmpty()){
            for(FamousProduction production:productionList){
                logger.info("famousId:{}",production.getFamousId());
                logger.info("productionId:{}",production.getProductionId());
                logger.info("productionName:{}",production.getProductionName());
                logger.info("publishedYear:{}",production.getPublishedYear());
                logger.info("productionContent:{}",production.getProductionContent());
            }
        }
    }

}
