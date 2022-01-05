package com.example.springbootlearn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示读取配置
 */
@RestController
public class PropertiesController {

    @Value("${school.grade}")
    private Integer grade;

    @Value("${school.classnum}")
    private Integer classNum;

    /**
     * 获取年级和班级, 不通过传参, 而是通过配置
     */
    @GetMapping("/gradeclass")
    public String gradeClass() {
        return grade + "-" + classNum;
    }



}
