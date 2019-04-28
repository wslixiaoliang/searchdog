package com.art.service;

import com.art.beans.college.CollegeClassmates;
import java.util.List;
import java.util.Map;
public interface ICollegeClassmatesSV  {
    /**
     * 根据条件查询同学信息
     * @param map
     * @return
     */
   List<CollegeClassmates> getschoolmates(Map map);


}
