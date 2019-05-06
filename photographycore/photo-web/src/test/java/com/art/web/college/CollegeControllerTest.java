package com.art.web.college;

import com.art.beans.college.CollegeClassmates;
import com.art.beans.college.Result;
import com.art.web.WebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class CollegeControllerTest {

    @Autowired
    private CollegeController collegeController;

    @Test
    public void test(){
        CollegeClassmates classmates = new CollegeClassmates();
        classmates.setSex("女");
        Integer page = 1;
        Integer limit = 50;
        Result result = collegeController.getCollegeInfos(classmates,page,limit);
        if(null!= result && result.getBeans().size()>0){
            List<CollegeClassmates> list = result.getBeans();
            System.out.println("=====================查询结果如下==============");
            for(CollegeClassmates classmate:list){
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
}
