package com.imooc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ApiIgnore
@RequestMapping("/hello")
@RestController
public class HelloController {

    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public Object hello() {

        logger.info("hello");
        logger.debug("hello");
        logger.warn("hello");
        logger.error("hello");

        return "hello world";
    }



    @GetMapping("/setSession")
    public Object setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        session.setAttribute("userInfo", "newUser");
        session.setMaxInactiveInterval(3600);
        session.getAttribute("userInfo");
        session.removeAttribute("userInfo");

        return "setSession ok";
    }

    /**
     * forest
     *
     * @param userName
     * @return
     */
    @GetMapping("/requestWithParam")
    public String requestWithParam(@RequestParam("uname") String userName) {
        System.out.println(userName);
        return userName;
    }

}
