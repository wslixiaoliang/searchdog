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
     * 名人作品：条件查询
     * @param map
     * @return
     */
    List<FamousProduction> getProductionInfos(Map map);
}
