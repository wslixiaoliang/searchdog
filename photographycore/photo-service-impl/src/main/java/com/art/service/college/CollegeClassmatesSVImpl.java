package com.art.service.college;

import com.art.beans.college.CollegeClassmates;
import com.art.dao.college.CollegeClassmatesDAO;
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
    public List<CollegeClassmates> getSchoolmates(Map map) {
        return dao.getSchoolmates(map);
    }
}
