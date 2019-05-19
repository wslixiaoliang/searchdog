package com.art.service.famous;

import com.art.ServiceApplication;
import com.art.beans.famous.FamousPortrait;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class FamousPortraitSVImplTest {

    @Autowired(required = false)
    private IFamousPortraitSV famousPortraitSV;
    //info,error 级别皆可用，为什么debug级别不可用(log4j配置文件没被使用)
    private static Log LOGGER = LogFactory.getLog(FamousPortraitSVImplTest.class);

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
                LOGGER.info("---------info-----------"+portrait.getPortraitId()+"--------------------");
                LOGGER.info("---------info-----------"+portrait.getPortraitName()+"--------------------");
                LOGGER.info("---------info-----------"+portrait.getChineseName()+"--------------------");
                LOGGER.info("---------info-----------"+portrait.getRelativeLocation()+"--------------------");
                LOGGER.error("---------error-----------"+portrait.getPortraitId()+"--------------------");
                LOGGER.error("---------error-----------"+portrait.getPortraitName()+"--------------------");
                LOGGER.error("---------error-----------"+portrait.getChineseName()+"--------------------");
                LOGGER.error("---------error-----------"+portrait.getRelativeLocation()+"--------------------");
            }
        }
    }





}
