package com.art.service.consumer;

import com.art.beans.consumer.ConsumerInformation;

import java.util.List;
import java.util.Map;

public interface IConsumerInformationSV {

    /**
     * 新增用户信息
     * @param map
     */

    void addConsumerInformation(Map map);

    /**
     * 查询用户信息
     * @param map
     */

    List<ConsumerInformation> getConsumerInfos(Map map);


    /**
     * 删除用户信息
     * @param map
     */

    void deleteConsumerInformation(Map map);

}
