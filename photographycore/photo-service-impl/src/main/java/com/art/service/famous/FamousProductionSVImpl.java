package com.art.service.famous;

import com.alibaba.dubbo.config.annotation.Service;
import com.art.beans.famous.FamousProduction;
import com.art.dao.famous.FamousProductionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;
/**
 * 世界名人作品SV实现
 * @author wslixiaoliang
 */
@Service
public class FamousProductionSVImpl implements IFamousProductionSV{

    @Autowired
    private FamousProductionDAO dao;
    /**
     * 名人作品：条件查询
     * @param map
     * @return
     */
    @Override
    public List<FamousProduction> getProductionInfos(Map map) {
        return dao.getProductionInfos(map);
    }
}
