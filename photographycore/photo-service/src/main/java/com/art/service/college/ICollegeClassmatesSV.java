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
     * 主键查询：大学同学
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
