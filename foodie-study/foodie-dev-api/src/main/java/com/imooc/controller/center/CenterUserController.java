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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        @RequestBody @Valid CenterUserBO centerUserBO,
        BindingResult bindingResult,
        HttpServletRequest request, HttpServletResponse response) {

        System.out.println(centerUserBO);

        // ------------------------ 校验 ------------------------
        // 判断BindingResult是否保存错误的验证信息，如果有，则直接return
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrors(bindingResult);
            return JsonResult.errorMsg(JsonUtils.objectToJson(errors));
        }


        // ------------------------ handle ------------------------
        // 更新后的用户信息
        Users userResult = centerUserService.updateUserInfo(userId, centerUserBO);

        // ------------------------ 设置cookie ------------------------
        setNullProperty(userResult);

        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        // todo: 后续要改，，会整合进redis，分布式会话, 增加令牌token

        return JsonResult.ok();
    }

    /**
     * 错误处理
     *
     * @param bindingResult
     * @return
     */
    private Map<String, String> getErrors(BindingResult bindingResult) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError error : fieldErrors) {
            String field = error.getField();
            String message = error.getDefaultMessage();
            map.put(field, message);
        }
        return map;
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
