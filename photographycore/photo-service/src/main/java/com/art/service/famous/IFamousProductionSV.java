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
     * 主键查询
     * @param map
     * @return
     */
    FamousProduction getProductionById(Map map);
}
