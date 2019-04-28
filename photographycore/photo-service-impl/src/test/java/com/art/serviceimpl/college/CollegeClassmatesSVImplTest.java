package com.art.serviceimpl.college;

import com.art.beans.college.CollegeClassmates;
import com.art.dao.college.CollegeClassmatesDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import  org.apache.log4j.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CollegeClassmatesSVImplTest{
    @Autowired(required = false)
    private CollegeClassmatesDAO collegeClassmatesDAO;
    private final Logger logger = Logger.getLogger(CollegeClassmatesSVImplTest.class);

    @Test
    public void getClassmates(){
        long classmataId = 1;
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("classmataId",classmataId);
        List<CollegeClassmates> classmates = collegeClassmatesDAO.getSchoolmates(queryMap);
        for(CollegeClassmates classmate:classmates)
        logger.info(classmate.getNickName());

    }

}
