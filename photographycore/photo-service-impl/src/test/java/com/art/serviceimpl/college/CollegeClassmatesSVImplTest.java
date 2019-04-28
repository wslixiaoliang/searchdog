package com.art.serviceimpl.college;

import com.art.ServiceApplication;
import com.art.beans.college.CollegeClassmates;
import com.art.service.college.ICollegeClassmatesSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import  org.apache.log4j.Logger;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
@WebAppConfiguration

public class CollegeClassmatesSVImplTest{

    @Autowired(required = false)
    private ICollegeClassmatesSV collegeClassmatesSV;
    private final Logger logger = Logger.getLogger(CollegeClassmatesSVImplTest.class);

    @Test
    public void testGetClassmates(){
        long classmataId = 1;
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("classmataId",classmataId);
        List<CollegeClassmates> classmates = collegeClassmatesSV.getSchoolmates(queryMap);
        for(CollegeClassmates classmate:classmates)
        logger.info(classmate.getNickName());

    }

}
