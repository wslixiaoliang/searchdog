package com.art.serviceimpl.college;

import com.alibaba.dubbo.config.annotation.Service;
import com.art.beans.college.CollegeClassmates;
import com.art.dao.CollegeClassmatesDAO;
import com.art.service.ICollegeClassmatesSV;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;

@Service
public class CollegeClassmatesSVImpl implements ICollegeClassmatesSV{

    @Autowired
    private CollegeClassmatesDAO collegeClassmatesDAO;

    /**
     * 条件查询:同学信息
     * @param map
     * @return
     */
    @Override
    public List<CollegeClassmates> getschoolmates(Map map) {
        return collegeClassmatesDAO.getschoolmates(map);
    }
}
