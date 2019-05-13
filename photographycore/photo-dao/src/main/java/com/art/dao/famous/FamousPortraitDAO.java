package com.art.dao.famous;

import com.art.beans.college.FamousPortrait;

import java.util.List;
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
}
