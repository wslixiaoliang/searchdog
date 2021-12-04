package com.art.elastic.dao;

import com.art.elastic.vo.FamousPortrait;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 世界名人肖像DAO
 * @author wslixiaoliang
 */
public interface FamousPortraitMapper {

    /**
     * 主键查询
     * @param map
     * @return
     */
    FamousPortrait getPortraitById(Map map);

    /**
     * 多主键查询:多个主键查询多条记录
     * @param map
     * @return
     */
    List<FamousPortrait> getfamousListByIds(Map map);

    /**
     * 肖像list查询
     * @return
     */
    List<FamousPortrait> getPortraitInfos(Map map);

    /**
     * 查询 名人总数据条数
     * @return
     */
    Integer getFamousCount( Map map );



}
