package com.art.elastic.dao;

import com.art.elastic.vo.FamousProduction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 世界名人作品DAO
 * @author wslixiaoliang
 */
public interface FamousProductionMapper {

    /**
     * 名人作品：条件查询
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
