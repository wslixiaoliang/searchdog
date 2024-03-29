/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.elastic.service;

import com.art.elastic.dao.FamousPortraitDao;
import com.art.elastic.vo.FamousPortrait;
import org.elasticsearch.common.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 世界名人肖像SV实现
 * @author wslixiaoliang
 */
@Service
public class FamousPortraitSVImpl implements IFamousPortraitSV{

    @Autowired
    private FamousPortraitDao dao;

    /**
     * 主键查询
     * @param map
     * @return
     */
    @Override
    public FamousPortrait getPortraitById(Map map) {
        return dao.getPortraitById(map);
    }

    /**
     * 主键查询:多个主键查询多条记录
     * @param map
     * @return
     */
    @Override
    public List<FamousPortrait> getfamousListByIds(Map map) {
        return dao.getfamousListByIds(map);
    }

    /**
     * 肖像list查询
     * @return
     */
    @Override
    public List<FamousPortrait> getPortraitInfos(Map map) {
        return dao.getPortraitInfos(map);
    }

    @Override
    public Integer getFamousCount(Map map) {
        return dao.getFamousCount(map);
    }
}
