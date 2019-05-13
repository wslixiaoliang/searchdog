package com.art.service.famous;

import com.art.ServiceApplication;
import com.art.beans.famous.WorldFamous;
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
public class WorldFamousSVImplTest {

    @Autowired
    private IWorldFamousSV worldFamousSV;
    private final Logger logger = Logger.getLogger(WorldFamousSVImplTest.class);
    /**
     * 主键查询
     */
    @Test
    public void testGetClassmates(){
        long classmateId = 9;
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("classmateId",classmateId);
        WorldFamous famous = worldFamousSV.getFamousById(queryMap);
        System.out.println(famous.getFamousId());
        System.out.println(famous.getChineseName());
        System.out.println(famous.getEnglishName());
        System.out.println(famous.getSex());
        System.out.println(famous.getCareer());
        System.out.println(famous.getAchievement());
        System.out.println(famous.getHonor());
        System.out.println(famous.getCountry());
        System.out.println(famous.getBirthYear());
    }

    /**
     * 条件查询
     */
    @Test
    public void testGetSchoolmateInfos(){
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("chineseName","");
        queryMap.put("sex","女");
        queryMap.put("career","");
        queryMap.put("achievement","");
        queryMap.put("honor","");
        queryMap.put("country","");
        List<WorldFamous> famousList = worldFamousSV.getFamousInfos(queryMap);
        for(WorldFamous famous:famousList){
            System.out.println(famous.getFamousId());
            System.out.println(famous.getChineseName());
            System.out.println(famous.getEnglishName());
            System.out.println(famous.getSex());
            System.out.println(famous.getCareer());
            System.out.println(famous.getAchievement());
            System.out.println(famous.getHonor());
            System.out.println(famous.getCountry());
            System.out.println(famous.getBirthYear());
            System.out.println("=========================");
        }
    }

    /**
     * 新增
     */
    @Test
    public void testAddSchoolmates(){
        long famousId = 3;
        String chineseName = "山本耀司";
        String englishName = "";
        String sex= "男";
        String career = "";
        String achievement = "";
        String honor = "";
        String country = "日本";
        String birthYear = "";
        Map map = new HashMap();

        map.put("famousId",famousId);
        map.put("chineseName",chineseName);
        map.put("englishName",englishName);
        map.put("sex",sex);
        map.put("career",career);
        map.put("achievement",achievement);
        map.put("honor",honor);
        map.put("country",country);
        map.put("birthYear",birthYear);

        worldFamousSV.addFamous(map);//新增
        //查询是否新增成功
        Map params = new HashMap();
        params.put("famousId",famousId);
        WorldFamous famous = worldFamousSV.getFamousById(params);
        System.out.println(famous.getFamousId());
        System.out.println(famous.getChineseName());
        System.out.println(famous.getEnglishName());
        System.out.println(famous.getSex());
        System.out.println(famous.getCareer());
        System.out.println(famous.getAchievement());
        System.out.println(famous.getHonor());
        System.out.println(famous.getCountry());
        System.out.println(famous.getBirthYear());
    }

    /**
     * 删除
     */
    @Test
    public void testDeleteByClassmatsId(){
        long famousId = 2;
        List famousIdList = new ArrayList();
        famousIdList.add(famousId);
        Map map = new HashMap();
        map.put("famousIds",famousIdList);
        worldFamousSV.deleteByFamousId(map);
        Map params = new HashMap();
        params.put("famousId",famousId);
        WorldFamous famous = worldFamousSV.getFamousById(params);
        if(null==famous){
            System.out.println("================主键为："+famousId+"的数据删除成功====================");
        }
    }
}
