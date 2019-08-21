package com.art.web.component;

import com.art.beans.elastic.SearchResult;
import com.art.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class FamousIndexComponentTest {

    @Autowired
    private FamousIndexComponent famousIndexComponent;

    @Test
    public void testIndexCreate(){
        String famous = "1,2,3,4,5,6,7,8,9,10,11,12";
        String [] famousIds = famous.split(",");
        List<String> famousList = new ArrayList<>();
        for(String id:famousIds){
            famousList.add(id);
        }
        SearchResult searchResult = famousIndexComponent.portraitIndex(famousList);
        System.out.println(""+searchResult.getTotalCount());
        System.out.println(""+searchResult.getStatus());
    }




}
