package com.art.service.famous;

import com.alibaba.dubbo.config.annotation.Service;
import com.art.beans.famous.Famous;
import com.art.dao.famous.FamousDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/**
 * 世界名人Service实现
 * @author lixiaoliang
 */
@Service
public class FamousSVImpl implements IFamousSV {

    @Autowired
    private FamousDAO dao;
    /**
     * 主键查询
     * @param map
     * @return
     */
    @Override
    public Famous getFamousById(Map map) {
        return dao.getFamousById(map);
    }

    /**
     * 条件查询
     * @param map
     * @return
     */
    @Override
    public List<Famous> getFamousInfos(Map map) {
        return dao.getFamousInfos(map);
    }

    /**
     * count 查询
     * @param map
     * @return
     */
    @Override
    public Integer getFamousCount(Map map) {
        return dao.getFamousCount(map);
    }

    /**
     * 新增
     * @param map
     */
    @Override
    public void addFamous(Map map) {
        dao.addFamous(map);
    }

    /**
     * 删除
     * @param map
     */
    @Override
    public void deleteByFamousId(Map map) {
        dao.deleteByFamousId(map);
    }


}
