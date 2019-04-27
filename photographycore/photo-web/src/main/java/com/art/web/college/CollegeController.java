package com.art.web.college;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.beans.college.CollegeClassmates;
import com.art.beans.college.Result;
import com.art.service.college.ICollegeClassmatesSV;
import com.art.util.Constans;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大学同学Controller
 * @author lixiaoliang
 */
@RestController
@RequestMapping(value = "/college")
public class CollegeController {

    @Reference
    private ICollegeClassmatesSV collegeClassmatesSV;
    private static  final Logger logger = Logger.getLogger(CollegeController.class);

    /**
     * 根据条件查询：大学同学信息
     * @param collegeClassmates
     */
    @RequestMapping(value = "/getCollegeInfos",method = RequestMethod.GET)
    public Result getCollegeInfos(CollegeClassmates collegeClassmates,Integer page,Integer limit){

        Result result = new Result();

        try{
            if(null== collegeClassmates) return null;
            Map<String,Object> queryMap = this.changeBean2Map(collegeClassmates);
            queryMap.put(Constans.START,limit*(page-1));
            queryMap.put(Constans.LIMIT,limit);
            List<CollegeClassmates> classmateList= collegeClassmatesSV.getSchoolmates(queryMap);
            result.setReturnCode(Constans.SUCESSS_RETURN_CODE);
            result.setBeans(classmateList);

        }catch (Exception e){

            logger.info(e.getMessage(),e);
            result.setReturnCode(Constans.FAILURE_RETURN_CODE);
        }
        return result;
    }

    /**
     * bean 转化城 map
     * @param collegeClassmates
     * @return
     */
    private Map<String,Object> changeBean2Map(CollegeClassmates collegeClassmates){

        String nickName = collegeClassmates.getNickName();
        String sex = collegeClassmates.getSex();
        String phoneNumber = collegeClassmates.getPhoneNumber();
        String mailBox = collegeClassmates.getMailBox();
        String address = collegeClassmates.getAddress();

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("nickName",nickName);
        queryMap.put("sex",sex);
        queryMap.put("phoneNumber",phoneNumber);
        queryMap.put("mailBox",mailBox);
        queryMap.put("address",address);

        return queryMap;


    }



}
