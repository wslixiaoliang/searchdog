package com.art.service.college;

import com.art.ServiceApplication;
import com.art.beans.college.CollegeClassmates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
        long classmateId = 9;
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("classmateId",classmateId);
        CollegeClassmates classmate = collegeClassmatesSV.getSchoolmateById(queryMap);
        System.out.println(classmate.getNickName());
        System.out.println(classmate.getSex());
        System.out.println(classmate.getPhoneNumber());
        System.out.println(classmate.getMailBox());
        System.out.println(classmate.getAddress());
    }

    /**
     * 条件查询
     */
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

    /**
     * 新增
     */
    @Test
    public void testAddSchoolmates(){
        long classmateId = 9;
        String nickName = "王宝康";
        String sex= "男";
        String phoneNumber = "15979436659";
        String mailBox = "76564@qq.com";
        String address = "西安";
        Map map = new HashMap();
        map.put("classmateId",classmateId);
        map.put("nickName",nickName);
        map.put("sex",sex);
        map.put("phoneNumber",phoneNumber);
        map.put("mailBox",mailBox);
        map.put("address",address);
        collegeClassmatesSV.addClassmates(map);
        Map params = new HashMap();
        params.put("classmateId",classmateId);
        CollegeClassmates  classmate = collegeClassmatesSV.getSchoolmateById(params);
        System.out.println(classmate.getNickName());
        System.out.println(classmate.getSex());
        System.out.println(classmate.getPhoneNumber());
        System.out.println(classmate.getMailBox());
        System.out.println(classmate.getAddress());
    }

    /**
     * 删除
     */
    @Test
    public void testDeleteByClassmatsId(){
        long clasamateId = 2;
        List classmateList = new ArrayList();
        classmateList.add(clasamateId);
        Map map = new HashMap();
        map.put("classmateIds",classmateList);
        collegeClassmatesSV.deleteByClassmatsId(map);
        Map params = new HashMap();
        params.put("classmateId",clasamateId);
        CollegeClassmates classmate = collegeClassmatesSV.getSchoolmateById(params);
        if(null==classmate){
            System.out.println("================主键为："+clasamateId+"的数据删除成功====================");
        }
    }
}
