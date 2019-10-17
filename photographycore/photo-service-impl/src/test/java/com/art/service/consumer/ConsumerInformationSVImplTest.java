package com.art.service.consumer;

import com.art.ServiceApplication;
import com.art.beans.consumer.ConsumerInformation;
import com.art.util.CommonUtil;
import com.art.util.SearchConstans;
import com.art.util.DateUtil;
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
 * 用户信息测试类
 * @author wslixiaoliang
 * 当@Autowired注解的时候，其实默认就是@Autowired(required=true)，表示注入的时候，该bean必须存在，否则就会注入失败;
 * 若@Autowired(required=false)：表示忽略当前要注入的bean，如果有直接注入，没有跳过，不会报错；
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class ConsumerInformationSVImplTest {

    @Autowired(required = false)
    private IConsumerInformationSV consumerInformationSV;
    private static Log LOGGER = LogFactory.getLog(ConsumerInformationSVImplTest.class);

    @Test
    public void testConsumerInformation(){

        Map map = new HashMap();

        long consumerId = CommonUtil.getPrimaryKey();
        String consumerName = "史蒂芬·乔布斯";
        String sex = "男";
        String birthday = "2019-06-18";
        String city = "洛杉矶";
        String sign = "happy life happy coding";
        String phoneNumber = "12900987769";
        String consumerPassword = "66666666";

        map.put("consumerId",consumerId);
        map.put("consumerName",consumerName);
        map.put("sex",sex);
        map.put("birthday",birthday);
        map.put("city",city);
        map.put("sign",sign);
        map.put("phoneNumber",phoneNumber);
        map.put("consumerPassword",consumerPassword);
        map.put("registTime","2019-07-17 08:20:59");

        //新增
        consumerInformationSV.addConsumerInformation(map);

        //查询
        List<ConsumerInformation> list = consumerInformationSV.getConsumerInfos(map);
        for(ConsumerInformation consumer:list){
            String consumerTime = DateUtil.date2String(consumer.getRegistTime(), SearchConstans.YYYY_MM_DD_HH_MM_SS);
            String consumerNm = consumer.getConsumerName();
            LOGGER.info("================="+consumer.getConsumerName()+"===============");
        }

        //删除
        Map params = new HashMap();
        params.put("consumerId","2019071611462150254");
        consumerInformationSV.deleteConsumerInformation(params);
        consumerInformationSV.deleteConsumerInformation(map);


    }







}
