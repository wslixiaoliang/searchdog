package com.art.serviceimpl.college;


import com.art.beans.college.CollegeClassmates;
import com.art.dao.college.CollegeClassmatesDAO;
import com.art.service.college.ICollegeClassmatesSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * 大学同学SVImpl
 * @author lixiaoliang
 */
@Service
public class CollegeClassmatesSVImpl implements ICollegeClassmatesSV {

    @Autowired
    private CollegeClassmatesDAO collegeClassmatesDAO;
    /**
     * 条件查询：大学同学
     * @param map
     * @return
     */
    @Override
    public List<CollegeClassmates> getSchoolmates(Map map) {
        return collegeClassmatesDAO.getSchoolmates(map);
    }
}
