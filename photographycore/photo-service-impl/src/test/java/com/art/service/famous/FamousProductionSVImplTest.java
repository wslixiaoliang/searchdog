package com.art.service.famous;

import com.art.ServiceApplication;
import com.art.beans.famous.FamousPortrait;
import com.art.beans.famous.FamousProduction;
import com.art.dao.famous.FamousPortraitDAO;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
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
                System.out.println(production.getFamousId());
                System.out.println(production.getProductionId());
                System.out.println(production.getProductionName());
                System.out.println(production.getPublishedYear());
                System.out.println(production.getProductionContent());
            }
        }
    }

}
