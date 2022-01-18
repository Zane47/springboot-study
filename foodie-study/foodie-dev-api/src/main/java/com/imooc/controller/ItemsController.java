package com.imooc.controller;

import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
import com.imooc.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "items api", tags = {"items information presentation api"})
@RequestMapping("items")
@RestController
public class ItemsController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询商品详情
     */
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


    /**
     * 查询商品评价等级数量信息
     */
    @ApiOperation(value = "getItemCommentLevel", notes = "getItemCommentLevel", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public JsonResult getItemCommentLevel(
            @ApiParam(name = "itemId", value = "item id", required = true)
            @RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return JsonResult.errorMsg("wrong item id");
        }

        CommentLevelCountsVO countsVO = itemService.queryCommentCounts(itemId);

        return JsonResult.ok(countsVO);
    }

    /**
     * 查询商品评论信息
     */
    @ApiOperation(value = "queryItemComments", notes = "queryItemComments", httpMethod = "GET")
    @GetMapping("/comments")
    public JsonResult comments(
            @RequestParam String itemId,
            @RequestParam Integer level,
            @RequestParam Integer page,
            @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return JsonResult.errorMsg("wrong item id");
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }


        return JsonResult.ok();
    }


}
