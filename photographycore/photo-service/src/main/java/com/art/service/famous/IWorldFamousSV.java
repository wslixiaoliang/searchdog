package com.art.service.famous;

import com.art.beans.college.WorldFamous;

import java.util.List;
import java.util.Map;

/**
 * 世界名人Service
 * @author lixiaoliang
 */
public interface IWorldFamousSV {

    /**
     * 主键查询
     * @param map
     * @return
     */
    WorldFamous getFamousById(Map map);

    /**
     * 条件查询
     * @param map
     * @return
     */
    List<WorldFamous> getFamousInfos(Map map);

    /**
     * 新增
     * @param map
     */
    void addFamous(Map map);

    /**
     * 删除
     * @param map
     */
    void deleteByFamousId(Map map);

}
