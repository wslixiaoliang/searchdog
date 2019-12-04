package com.art.web.component;

import com.art.beans.elastic.SearchResult;
import com.art.web.WebApplication;
import com.art.web.component.famous.IndexProductionComponent;
import com.art.web.component.famous.IndexFamousComponent;
import com.art.web.component.famous.IndexSuggestionComponent;
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
    private IndexFamousComponent indexFamousComponent;
    @Autowired
    private IndexProductionComponent indexProductionComponent;
    @Autowired
    private IndexSuggestionComponent indexSuggestionComponent;

    @Test
    public void testIndexCreate() {
        String famous = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16";

        String[] famousIds = famous.split(",");
        List<String> famousList = new ArrayList<>();
        for (String id : famousIds) {
            famousList.add(id);
        }
//        SearchResult searchResult = indexFamousComponent.indexFamous(famousList);
//        SearchResult searchResult = indexProductionComponent.productionIndex(famousList);

        String suggestion = "1";
        String[] suggestions = suggestion.split(",");
        List<String> suggestionList = new ArrayList<>();
        for (String id : suggestions) {
            suggestionList.add(id);
        }
        SearchResult searchResult = indexSuggestionComponent.indexSuggestions(suggestionList);
        System.out.println("新增文档结果==========================结果状态：" + searchResult.getStatus() + " ====成功条数：" + searchResult.getTotalCount() + " ====结果信息：" + searchResult.getReturnMsg());
        System.out.println("" + searchResult.getStatus());
    }
}
