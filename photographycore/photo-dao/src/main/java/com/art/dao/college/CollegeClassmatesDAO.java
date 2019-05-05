package com.art.dao.college;

import com.art.beans.college.CollegeClassmates;
import java.util.List;
import java.util.Map;

/**
 * 大学同学DAO
 * @author lixiaoliang
 */

public interface CollegeClassmatesDAO {

    /**
     * 条件查询：大学同学
     * @param map
     * @return
     */
    CollegeClassmates getSchoolmateById(Map map);

    /**
     * 条件查询：大学同学
     * @param map
     * @return
     */
    List<CollegeClassmates> getSchoolmateInfos(Map map);

    /**
     * 新增：大学同学
     * @param map
     */
    void addClassmates(Map map);

}
