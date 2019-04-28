package com.art.dao;

import com.art.beans.college.CollegeClassmates;
import java.util.List;
import java.util.Map;

public interface CollegeClassmatesDAO {

    /**
     * 条件查询:同学信息
     * @param map
     * @return
     */
    List<CollegeClassmates> getschoolmates(Map map);
}
