package com.art.service.consumer;

import com.alibaba.dubbo.config.annotation.Service;
import com.art.beans.consumer.ConsumerInformation;
import com.art.dao.consumer.ConsumerInformationDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 用户信息实现SV实现
 * @author wslixiaoliang
 */
@Service
public class ConsumerInformationSVImpl implements IConsumerInformationSV{

   @Autowired
   private ConsumerInformationDAO dao;


    /**
     * 新增：用户信息
     * @param map
     */
    @Override
    public void addConsumerInformation(Map map) {

        dao.addConsumerInformation(map);
    }

    /**
     * 查询：用户信息
     * @param map
     * @return
     */
    @Override
    public List<ConsumerInformation> getConsumerInfos(Map map) {
        return dao.getConsumerInfos(map);
    }

    /**
     * 删除：用户信息
     * @param map
     */
    @Override
    public void deleteConsumerInformation(Map map) {

        dao.deleteConsumerInformation(map);

    }
}
