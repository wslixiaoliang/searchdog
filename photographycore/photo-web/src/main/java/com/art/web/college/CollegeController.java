package com.art.web.college;

import com.alibaba.dubbo.config.annotation.Reference;
import com.art.service.college.ICollegeClassmatesSV;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/college")
public class CollegeController {

    @Reference
    private ICollegeClassmatesSV collegeClassmatesSV;

}
