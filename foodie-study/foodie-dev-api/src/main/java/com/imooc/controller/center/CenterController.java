package com.imooc.controller.center;

import com.imooc.pojo.Users;
import com.imooc.service.CenterUserService;
import com.imooc.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "user center", tags = {"用户中心展示的相关接口"})
@RestController
@RequestMapping("center")
public class CenterController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "get user info", notes = "get user info", httpMethod = "GET")
    @GetMapping("/userInfo")
    public JsonResult getUserInfo(
            @ApiParam(name = "user id", value = "userId", required = true)
            @RequestParam String userId) {
        Users users = centerUserService.queryUserInfoById(userId);
        users.setPassword(null);

        return JsonResult.ok(users);
    }


}
