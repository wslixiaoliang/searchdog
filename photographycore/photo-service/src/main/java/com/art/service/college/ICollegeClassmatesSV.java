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
     * 主键查询
     * @param map
     * @return
     */
    CollegeClassmates getSchoolmateById(Map map);

    /**
     * 条件查询
     * @param map
     * @return
     */
    List<CollegeClassmates> getSchoolmateInfos(Map map);

    /**
     * 新增
     * @param map
     */
    void addClassmates(Map map);

    /**
     * 删除
     * @param map
     */
    void deleteByClassmatsId(Map map);

}
