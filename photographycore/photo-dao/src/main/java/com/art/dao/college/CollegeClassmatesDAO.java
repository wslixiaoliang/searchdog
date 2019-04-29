package com.art.dao.college;

import com.art.beans.college.CollegeClassmates;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * 大学同学DAO
 * @author lixiaoliang
 */
@Mapper
public interface CollegeClassmatesDAO {

    /**
     * 条件查询：大学同学
     * @param map
     * @return
     */
    List <CollegeClassmates> getSchoolmates(Map map);
}
