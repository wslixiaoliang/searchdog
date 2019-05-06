package com.art.service.college;

import com.alibaba.dubbo.config.annotation.Service;
import com.art.beans.college.CollegeClassmates;
import com.art.dao.college.CollegeClassmatesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

@Service
public class CollegeClassmatesSVImpl implements ICollegeClassmatesSV{

    @Autowired
    private CollegeClassmatesDAO dao;
    /**
     * 主键查询
     * @param map
     * @return
     */
    @Override
    public CollegeClassmates getSchoolmateById(Map map) {
        return dao.getSchoolmateById(map);
    }

    /**
     * 条件查询
     * @param map
     * @return
     */
    @Override
    public List<CollegeClassmates> getSchoolmateInfos(Map map) {
        return dao.getSchoolmateInfos(map);
    }

    /**
     * 新增
     * @param map
     */
    @Override
    public void addClassmates(Map map) {
        dao.addClassmates(map);
    }

    /**
     * 删除
     * @param map
     */
    @Override
    public void deleteByClassmatsId(Map map) {
        dao.deleteByClassmatsId(map);
    }


}
