package com.art.service.college;

import com.art.beans.college.CollegeClassmates;
import com.art.dao.college.CollegeClassmatesDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CollegeClassmatesSVImpl implements ICollegeClassmatesSV{

    @Autowired
    private CollegeClassmatesDAO dao;

    /**
     * 条件查询:同学信息
     * @param map
     * @return
     */
    @Override
    public CollegeClassmates getSchoolmateById(Map map) {
        return dao.getSchoolmateById(map);
    }

    /**
     * 条件查询：大学同学
     * @param map
     * @return
     */
    @Override
    public List<CollegeClassmates> getSchoolmateInfos(Map map) {
        return dao.getSchoolmateInfos(map);
    }

    /**
     * 新增：大学同学
     * @param map
     */
    @Override
    public void addClassmates(Map map) {
        dao.addClassmates(map);
    }


}
