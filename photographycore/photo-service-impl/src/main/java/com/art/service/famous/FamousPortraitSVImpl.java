package com.art.service.famous;

import com.alibaba.dubbo.config.annotation.Service;
import com.art.beans.famous.FamousPortrait;
import com.art.dao.famous.FamousPortraitDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

/**
 * 世界名人肖像SV实现
 * @author wslixiaoliang
 */
@Service
public class FamousPortraitSVImpl implements IFamousPortraitSV{

    @Autowired
    private FamousPortraitDAO dao;

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
}
