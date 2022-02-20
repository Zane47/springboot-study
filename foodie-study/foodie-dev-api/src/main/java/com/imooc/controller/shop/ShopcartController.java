package com.imooc.controller.shop;

import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "shopcartapi", tags = {"shopping cart api"})
@RequestMapping("shopcart")
@RestController
public class ShopcartController {

    /**
     * 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
     */
    @ApiOperation(value = "addToShopcart", notes = "add to shop cart", httpMethod = "POST")
    @PostMapping("/add")
    public JsonResult add(
            @RequestParam String userId,
            @RequestBody ShopcartBO shopcartBO) {

        // 加入购物车时需要判断权限, 涉及到拦截器等, -> 分布式缓存Redis
        if (StringUtils.isBlank(userId)) {
            return JsonResult.errorMsg("blank user id");
        }

        // todo:前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存


        System.out.println(shopcartBO);

        return JsonResult.ok();
    }


    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public JsonResult del(
            @RequestParam String userId,
            @RequestParam String itemSpecId,
            HttpServletRequest request,
            HttpServletResponse response) {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return JsonResult.errorMsg("参数不能为空");
        }

        // todo: 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的商品

        return JsonResult.ok();
    }

}
