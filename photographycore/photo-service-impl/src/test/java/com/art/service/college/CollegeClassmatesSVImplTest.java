package com.art.service.college;

import com.art.ServiceApplication;
import com.art.beans.college.CollegeClassmates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import  org.apache.log4j.Logger;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class CollegeClassmatesSVImplTest{

    @Autowired
    private ICollegeClassmatesSV collegeClassmatesSV;
    private final Logger logger = Logger.getLogger(CollegeClassmatesSVImplTest.class);

    @Test
    public void testGetClassmates(){
        long classmateId = 1;
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("classmateId",classmateId);
        List<CollegeClassmates> classmates = collegeClassmatesSV.getSchoolmates(queryMap);

        for(CollegeClassmates classmate:classmates){
            System.out.println(classmate.getNickName());
        }


    }

}
