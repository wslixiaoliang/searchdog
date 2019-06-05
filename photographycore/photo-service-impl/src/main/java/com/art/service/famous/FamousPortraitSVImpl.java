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
     * 查询肖像list
     * @return
     */
    @Override
    public List<FamousPortrait> getPortraitInfos(Map map) {
        return dao.getPortraitInfos(map);
    }

}
