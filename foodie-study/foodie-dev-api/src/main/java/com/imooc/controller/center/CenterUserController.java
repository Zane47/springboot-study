package com.imooc.controller.center;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.CenterUserBO;
import com.imooc.service.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "用户信息接口", tags = {"用户信息相关接口"})
@RestController
@RequestMapping("userInfo")
public class CenterUserController {

    @Autowired
    private CenterUserService centerUserService;



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
        @RequestBody CenterUserBO centerUserBO,
        HttpServletRequest request, HttpServletResponse response) {

        System.out.println(centerUserBO);

        // 更新后的用户信息
        Users userResult = centerUserService.updateUserInfo(userId, centerUserBO);

        // ------------------------ 设置cookie ------------------------
        setNullProperty(userResult);

        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        // todo: 后续要改，，会整合进redis，分布式会话, 增加令牌token

        return JsonResult.ok();
    }


    /**
     * 敏感信息设置为null, 给cookie
     *
     * @param user
     */
    private void setNullProperty(Users user) {
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setBirthday(null);
    }
}
