package com.example.springbootlearn;

import org.springframework.web.bind.annotation.*;

/**
 * 演示传参形式
 */
@RestController
// @RequestMapping("/stu")
public class ParamController {

    // 简单请求
    @GetMapping("/firstrequest")
    public String firstRequest() {
        return "firstRequest";
    }

    // 参数
    @GetMapping("/requestparam")
    public String requestParam(@RequestParam Integer num) {
        return "param from request: " + num;
    }

    // 路径参数
    @GetMapping("/pathparam/{num}")
    public String pathParam(@PathVariable Integer num) {
        return "param from path: " + num;
    }

    // 接口对应多url
    @GetMapping({"/multiurl1", "/multiurl2"})
    public String multiUrl(@RequestParam Integer num) {
        return "param: " + num;
    }

    // 参数非必传
    @GetMapping("/required")
    public String required(@RequestParam(required = false, defaultValue = "0") Integer num) {
        return "required: " + num;
    }


}
