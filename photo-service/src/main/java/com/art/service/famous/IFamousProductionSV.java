/*
 * Copyright (c) 1989-2020 Wslixiaoliang@Searching.Co.Ltd. All rights reserved.
 */

package com.art.service.famous;

import com.art.beans.famous.FamousProduction;
import java.util.List;
import java.util.Map;

/**
 * 世界名人作品SV
 * @author wslixiaoliang
 */
public interface IFamousProductionSV {
    /**
     * 条件查询
     * @param map
     * @return
     */
    List<FamousProduction> getProductionInfos(Map map);

    /**
     * count查询
     * @param map
     * @return
     */
    Integer getProductionCount(Map map);

    /**
     * 单个&多个作品查询
     * @param map
     * @return
     */
    List<FamousProduction> getProductionById(Map map);
}
