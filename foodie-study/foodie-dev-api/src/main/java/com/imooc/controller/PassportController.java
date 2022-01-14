package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.JsonResult;
import com.imooc.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "register and login", tags = {"apis for register and login"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    /**
     * 用户名是否存在
     *
     * @param userName
     * @return 状态码
     */
    @ApiOperation(value = "check user name is existed",
            notes = "whether user name is existed", httpMethod = "GET")
    @GetMapping("/userNameIsExisted")
    public JsonResult userNameIsExisted(@RequestParam String userName) {
        // 判空
        if (StringUtils.isBlank(userName)) {
            return JsonResult.errorMsg("user name cannot be empty");
        }

        // 是否注册过
        if (userService.queryUserNameIsExisted(userName)) {
            // 注册过
            return JsonResult.errorMsg("user name already exists");
        }

        // 成功
        return JsonResult.ok();
        // return HttpStatus.OK.value();
    }

    /**
     * 创建用户
     *
     * @param userBO 前端输入数据
     * @return users
     */
    @ApiOperation(value = "register", notes = "register user", httpMethod = "POST")
    @PostMapping("/regist")
    public JsonResult regist(@RequestBody UserBO userBO) {
        // ------------------------ 校验 ------------------------
        // 元素不能为空
        if (StringUtils.isBlank(userBO.getUserName()) ||
                StringUtils.isBlank(userBO.getPassword()) ||
                StringUtils.isBlank(userBO.getConfirmedPassword())) {
            return JsonResult.errorMsg("error, empty paramter");
        }

        // 查询用户名是否存在
        if (userService.queryUserNameIsExisted(userBO.getUserName())) {
            return JsonResult.errorMsg("user name already exists");
        }

        // 密码长度
        if (StringUtils.length(userBO.getPassword()) < 6) {
            return JsonResult.errorMsg("the length of pwd is too short");
        }

        // 两次输入密码一致
        if (!StringUtils.equals(userBO.getPassword(), userBO.getConfirmedPassword())) {
            return JsonResult.errorMsg("inconsistent password");
        }

        // ------------------------ 注册 ------------------------
        Users user = userService.createUser(userBO);

        // null properties
        user.setPassword(null);
        user.setMobile(null);
        user.setEmail(null);
        user.setCreatedTime(null);
        user.setUpdatedTime(null);
        user.setBirthday(null);

        // ------------------------ return ------------------------
        return JsonResult.ok(user);
    }

    /**
     * 用户登陆
     *
     * @param userBO
     * @return
     */
    @ApiOperation(value = "user login", notes = "user login", httpMethod="POST")
    @PostMapping("/login")
    public JsonResult login(@RequestBody UserBO userBO) {
        // ------------------------ check ------------------------
        if (StringUtils.isBlank(userBO.getUserName()) || StringUtils.isBlank(userBO.getPassword())) {
            return JsonResult.errorMsg("empty parameter");
        }

        if (!userService.queryUserNameIsExisted(userBO.getUserName())) {
            return JsonResult.errorMsg("no user, pls register first");
        }

        try {
            final Users users = userService.queryUser4Login(userBO.getUserName(), MD5Utils.getMD5Str(userBO.getPassword()));
            if (null == users) {
                return JsonResult.errorMsg("wrong password");
            }


            return JsonResult.ok(users);
        } catch(Exception e) {
            return JsonResult.errorMsg(e.getMessage());
        }



    }


}
