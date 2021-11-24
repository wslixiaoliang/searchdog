package com.art.elastic.dao;

import com.art.elastic.vo.ConsumerInformation;
import java.util.List;
import java.util.Map;

/**
 * 用户信息DAO
 * @author wslixiaoliang
 */

public interface ConsumerInformationDAO
{

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
