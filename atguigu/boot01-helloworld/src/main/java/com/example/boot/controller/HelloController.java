package com.example.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody
// @Controller
// 这两个可以直接写成@RestController
@RestController
public class HelloController {

    // @ResponseBody 因为会有多个, 所以直接写到类上去
    @RequestMapping("/hello")
    public String handle01() {
        return "hello world, Springboot2";
    }

}
