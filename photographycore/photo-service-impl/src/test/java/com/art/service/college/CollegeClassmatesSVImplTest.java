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


    /**
     * 主键查询
     */
    @Test
    public void testGetClassmates(){
        long classmateId = 1;
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("classmateId",classmateId);
        List<CollegeClassmates> classmates = collegeClassmatesSV.getSchoolmateById(queryMap);
        for(CollegeClassmates classmate:classmates){
            System.out.println(classmate.getNickName());
            System.out.println(classmate.getPhoneNumber());
            System.out.println(classmate.getMailBox());
            System.out.println(classmate.getAddress());
        }
    }
    @Test
    public void testGetSchoolmateInfos(){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("nickName","");
        queryMap.put("sex","女");
        queryMap.put("phoneNumber","");
        queryMap.put("mailBox","");
        queryMap.put("address","");
        List<CollegeClassmates> classmates = collegeClassmatesSV.getSchoolmateInfos(queryMap);
        for(CollegeClassmates classmate:classmates){
            System.out.println(classmate.getClassmateId());
            System.out.println(classmate.getSex());
            System.out.println(classmate.getNickName());
            System.out.println(classmate.getPhoneNumber());
            System.out.println(classmate.getMailBox());
            System.out.println(classmate.getAddress());
            System.out.println("=========================");
        }
    }


}
