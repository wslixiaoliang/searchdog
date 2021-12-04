package com.art.elastic.service;

import com.art.elastic.ServiceApplication;
import com.art.elastic.vo.FamousPortrait;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 名人肖像SV测试类
 * @author wslixiaoliang
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class FamousPortraitSVImplTest {

    @Autowired(required = false)
    private IFamousPortraitSV famousPortraitSV;

    /**
     * 名人肖像：条件查询
     */
    @Test
    public void testGetPortraitInfos(){
        Map map = new HashMap();
        String chineseName = "爱因斯坦";
        map.put("chineseName",chineseName);
        List<FamousPortrait> portraitList = famousPortraitSV.getPortraitInfos(map);
        if(null!=portraitList && !portraitList.isEmpty()){
            for(FamousPortrait portrait:portraitList){
                logger.info("---------info-----------"+portrait.getPortraitId()+"--------------------");
                logger.info("---------info-----------"+portrait.getPortraitName()+"--------------------");
                logger.info("---------info-----------"+portrait.getChineseName()+"--------------------");
                logger.info("---------info-----------"+portrait.getRelativeLocation()+"--------------------");
                logger.error("---------error-----------"+portrait.getPortraitId()+"--------------------");
                logger.error("---------error-----------"+portrait.getPortraitName()+"--------------------");
                logger.error("---------error-----------"+portrait.getChineseName()+"--------------------");
                logger.error("---------error-----------"+portrait.getRelativeLocation()+"--------------------");
            }
        }
    }





}
