package com.art.dao.famous;

import com.art.beans.famous.FamousPortrait;

import java.util.List;
import java.util.Map;

/**
 * 世界名人肖像DAO
 * @author wslixiaoliang
 */
public interface FamousPortraitDAO {

    /**
     * 查询肖像list
     * @return
     */
    List<FamousPortrait> getPortraitInfos();

    /**
     * 主键查询
     * @param map
     * @return
     */
    FamousPortrait getPortraitById(Map map);
}
