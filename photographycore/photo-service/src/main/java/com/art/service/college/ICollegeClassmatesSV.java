package com.art.service.college;

import com.art.beans.college.CollegeClassmates;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 大学同学SV
 * @author lixiaoliang
 */
public interface ICollegeClassmatesSV  {

    /**
     * 条件查询：大学同学
     * @param map
     * @return
     */
    List<CollegeClassmates> getSchoolmates(Map map);


}
