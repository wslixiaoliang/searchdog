package com.art.serviceimpl.college;


import com.art.dao.college.CollegeClassmatesDAO;
import com.art.service.college.ICollegeClassmatesSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class CollegeClassmatesSVImpl implements ICollegeClassmatesSV {

    @Autowired(required = false)//允许null值
    private CollegeClassmatesDAO collegeClassmatesDAO;

    /**
     * 条件查询：大学同学
     * @param map
     * @return
     */
    @Override
    public List getSchoolmates(Map map) {
        return collegeClassmatesDAO.getSchoolmates(map);
    }
}
