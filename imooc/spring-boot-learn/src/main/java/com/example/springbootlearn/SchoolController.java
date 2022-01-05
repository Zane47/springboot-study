package com.example.springbootlearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SchoolController {

    @Resource
    // @Autowired
    private SchoolConfig schoolConfig;


    @GetMapping("/gradefromconfig")
    public String gradeClass() {
        return schoolConfig.grade + "-" + schoolConfig.classnum + "-" + schoolConfig.test;
    }


}
