package com.art.web.component;

import com.art.beans.elastic.SearchResult;
import com.art.web.WebApplication;
import com.art.web.component.famous.ProductionIndexComponent;
import com.art.web.component.famous.FamousIndexComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class IndexComponentTest {

    @Autowired
    private FamousIndexComponent famousIndexComponent;
    @Autowired
    private ProductionIndexComponent productionIndexComponent;

    @Test
    public void testIndexCreate() {
        String famous = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16";
        String[] famousIds = famous.split(",");
        List<String> famousList = new ArrayList<>();
        for (String id : famousIds) {
            famousList.add(id);
        }
        SearchResult searchResult = famousIndexComponent.indexFamous(famousList);
//        SearchResult searchResult = productionIndexComponent.productionIndex(famousList);
        System.out.println("新增文档结果==========================结果状态：" + searchResult.getStatus() + " ====成功条数：" + searchResult.getTotalCount() + " ====结果信息：" + searchResult.getReturnMsg());
        System.out.println("" + searchResult.getStatus());
    }
}
