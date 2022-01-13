package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.JSONResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/userNameIsExisted")
    public JSONResult userNameIsExisted(@RequestParam String userName) {
        // 判空
        if (StringUtils.isBlank(userName)) {
            return JSONResult.errorMsg("user name cannot be empty");
        }

        // 是否注册过
        if (userService.queryUserNameIsExisted(userName)) {
            // 注册过
            return JSONResult.errorMsg("user name already exists");
        }

        // 成功
        return JSONResult.ok();
        // return HttpStatus.OK.value();
    }

    /**
     * 创建用户
     *
     * @param userBO 前端输入数据
     * @return users
     */
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody UserBO userBO) {
        // ------------------------ 校验 ------------------------
        // 元素不能为空
        if (StringUtils.isBlank(userBO.getUserName()) ||
                StringUtils.isBlank(userBO.getPassword()) ||
                StringUtils.isBlank(userBO.getConfirmedPassword())) {
            return JSONResult.errorMsg("error, empty paramter");
        }

        // 查询用户名是否存在
        if (userService.queryUserNameIsExisted(userBO.getUserName())) {
            return JSONResult.errorMsg("user name already exists");
        }

        // 密码长度
        if (StringUtils.length(userBO.getPassword()) < 6) {
            return JSONResult.errorMsg("the length of pwd is too short");
        }

        // 两次输入密码一致
        if (!StringUtils.equals(userBO.getPassword(), userBO.getConfirmedPassword())) {
            return JSONResult.errorMsg("inconsistent password");
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
        return JSONResult.ok(user);
    }


}
