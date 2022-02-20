package com.imooc.controller.center;

import com.imooc.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户信息接口", tags = {"用户信息相关接口"})
@RestController
@RequestMapping("userInfo")
public class CenterUserController {


    /**
     *
     * @return
     */
    @ApiOperation(value = "updateUserInfo", notes = "updateUserInfo", httpMethod = "POST")
    @PostMapping("update")
    public JsonResult updateUserInfo(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId) {


        return JsonResult.ok();
    }


}
