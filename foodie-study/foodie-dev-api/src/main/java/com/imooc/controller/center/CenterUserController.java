package com.imooc.controller.center;

import com.imooc.pojo.bo.CenterUserBO;
import com.imooc.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户信息接口", tags = {"用户信息相关接口"})
@RestController
@RequestMapping("userInfo")
public class CenterUserController {


    /**
     * center更新用户信息
     *
     * @return
     */
    @ApiOperation(value = "updateUserInfo", notes = "updateUserInfo", httpMethod = "POST")
    @PostMapping("update")
    public JsonResult updateUserInfo(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @RequestBody CenterUserBO centerUserBO) {


        return JsonResult.ok();
    }


}
