/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.elastic.service;

import com.art.elastic.dao.FamousProductionDao;
import com.art.elastic.vo.FamousProduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 世界名人作品SV实现
 * @author wslixiaoliang
 */
@Service
public class FamousProductionSVImpl implements IFamousProductionSV{

    @Autowired(required=false)
    private FamousProductionDao dao;
    /**
     * 名人作品：条件查询
     * @param map
     * @return
     */
    @Override
    public List<FamousProduction> getProductionInfos(Map map) {
        return dao.getProductionInfos(map);
    }

    /**
     * count查询
     * @param map
     * @return
     */
    @Override
    public Integer getProductionCount(Map map) {
        return dao.getProductionCount(map);
    }

    /**
     * 单个&多个作品查询
     * @param map
     * @return
     */
    @Override
    public List<FamousProduction> getProductionById(Map map) {
        return dao.getProductionById(map);
    }
}
