package com.art.service.famous;

import com.art.beans.college.FamousPortrait;
import com.art.dao.famous.FamousPortraitDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/**
 * 世界名人肖像SV实现
 * @author wslixiaoliang
 */
public class FamousPortraitSVImpl implements IFamousPortraitSV{

    @Autowired
    private FamousPortraitDAO dao;
    /**
     * 查询肖像list
     * @return
     */
    @Override
    public List<FamousPortrait> getPortraitInfos() {
        return dao.getPortraitInfos();
    }
}
