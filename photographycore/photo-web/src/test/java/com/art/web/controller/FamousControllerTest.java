package com.art.web.controller;

import com.art.beans.famous.Famous;
import com.art.beans.famous.Result;
import com.art.util.SearchConstans;
import com.art.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class FamousControllerTest {

    @Autowired
    private FamousController collegeController;

    @Autowired
    private FileDownloadController fileDownloadController;

    @Test
    public void test(){
        Famous famous = new Famous();
        famous.setChineseName("屠呦呦");
        Integer page = 1;
        Integer limit = 50;
        Result result = collegeController.getWorldFamous(famous,page,limit);
        System.out.println("=====================查询结果如下==============");
        List<Famous> list = result.getBeans();
        if(result!=null){
            for(Famous famous1:list){
                System.out.println(famous1.getFamousId());
                System.out.println(famous1.getSex());
                System.out.println(famous1.getChineseName());
                System.out.println(famous1.getEnglishName());
                System.out.println(famous1.getCareer());
                System.out.println(famous1.getHonor());
                System.out.println(famous1.getAchievement());
                System.out.println(famous1.getCountry());
                System.out.println(famous1.getBirthYear());
                System.out.println("=========================");
            }
        }
    }
}

