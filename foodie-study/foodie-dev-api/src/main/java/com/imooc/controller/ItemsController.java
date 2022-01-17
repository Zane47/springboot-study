package com.imooc.controller;

import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
import com.imooc.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "items api", tags = {"items information presentation api"})
@RequestMapping("items")
@RestController
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "queryItemInfo", notes = "query item info", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JsonResult info(
            @ApiParam(name = "itemId", value = "item id", required = true)
            @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return JsonResult.errorMsg("wrong item id");
        }

        // 返回给前台的数据
        final ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(itemService.queryItemById(itemId));
        itemInfoVO.setItemImgList(itemService.queryItemImgList(itemId));
        itemInfoVO.setItemSpecList(itemService.queryItemSpecList(itemId));
        itemInfoVO.setItemParams(itemService.queryItemParam(itemId));

        return JsonResult.ok(itemInfoVO);
    }



}
