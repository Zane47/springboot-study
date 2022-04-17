package com.imooc.controller;

import com.imooc.forest.ForestWebUtils;
import com.imooc.forest.entity.ForestReqParamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ApiIgnore
@RequestMapping("/hello")
@RestController
public class HelloController {

    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private ForestWebUtils forestWebUtils;

    @GetMapping("/hello")
    public Object hello(HttpServletRequest request) {

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
    public String requestWithParam(HttpServletRequest request, @RequestParam("uname") String userName) {
        System.out.println(userName);
        return userName;
    }

    /**
     * post test
     *
     * @return
     */
    @PostMapping("/posthello")
    public String postHello(HttpServletRequest request) {
        System.out.println("posthello");
        return "hello, 8088 post";
    }

    /**
     * forest test
     */
    @GetMapping("/forest")
    public void helloForest() {
        ForestReqParamEntity entity = new ForestReqParamEntity();
        entity.setHttpUrl("http://localhost:8088/hello/posthello");
        entity.setType("post");
        String result = forestWebUtils.httpRequest(null, entity, String.class);
        System.out.println(result);
    }


}
