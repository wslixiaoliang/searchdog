package com.art.dao.college;

import java.util.List;
import java.util.Map;

public interface CollegeClassmatesDAO {

    /**
     * 条件查询：大学同学
     * @param map
     * @return
     */
    List getSchoolmates(Map map);
}
