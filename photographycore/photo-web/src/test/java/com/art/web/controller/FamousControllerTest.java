package com.art.web.controller;

import com.art.beans.famous.Famous;
import com.art.beans.famous.Result;
import com.art.util.Constans;
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


    @Test
    public void testTimeFormat(){

        //String类型时间格式
        String dataFormat = Constans.YYYYMMDD;
        String dataFormat1 = Constans.YYYYMMDDHHMMSS;
        String dataFormat2 = Constans.YYYYMMDDHHMMSSSSS;

        //标准类型时间格式
        String dataFormat3 = Constans.YYYY_MM_DD;
        String dataFormat4 = Constans.YYYY_MM_DD_HH_MM_SS;
        String dataFormat5 = Constans.YYYY_MM_DD_HH_MM_SS_SSS;



        String finalTime = fileDownloadController.getCurrentTime(dataFormat);
        System.out.println("=============================================="+finalTime);

        String finalTime1 = fileDownloadController.getCurrentTime(dataFormat1);
        System.out.println("=============================================="+finalTime1);

        String finalTime2 = fileDownloadController.getCurrentTime(dataFormat2);
        System.out.println("=============================================="+finalTime2);


        String finalTime3 = fileDownloadController.getCurrentTime(dataFormat3);
        System.out.println("=============================================="+finalTime3);

        String finalTime4 = fileDownloadController.getCurrentTime(dataFormat4);
        System.out.println("=============================================="+finalTime4);

        String finalTime5 = fileDownloadController.getCurrentTime(dataFormat5);
        System.out.println("=============================================="+finalTime5);

        System.out.println("============================================================================================");

    }
}

