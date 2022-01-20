package com.imooc.controller;

import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
import com.imooc.utils.JsonResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "items api", tags = {"items information presentation api"})
@RequestMapping("items")
@RestController
public class ItemsController extends BaseController {

    @Autowired
    private ItemService itemService;

    // ------------------------ 商品详情 ------------------------

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
            @ApiParam(name = "itemId", value = "itemId", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "comment level", required = false)
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "查询下一页的第几页", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数", required = false)
            @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return JsonResult.errorMsg("wrong item id");
        }

        // ------------------------ 设置默认值 ------------------------
        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        // ------------------------ handle ------------------------
        PagedGridResult pagedGridResult = itemService.queryPagedComments(itemId, level, page, pageSize);

        return JsonResult.ok(pagedGridResult);
    }


    // ------------------------ 搜索 ------------------------

    /**
     * 搜索商品列表
     */
    @ApiOperation(value = "searchitems", notes = "searchitems", httpMethod = "GET")
    @GetMapping("/search")
    public JsonResult searchByKeywords(
            @ApiParam(name = "keywords", value = "keywords", required = true)
            @RequestParam String keywords,
            @ApiParam(name = "sort", value = "sort", required = false)
            @RequestParam String sort,
            @ApiParam(name = "page", value = "page", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "pageSize", required = false)
            @RequestParam Integer pageSize) {

        if (StringUtils.isBlank(keywords)) {
            return JsonResult.errorMsg("keywords is null");
        }

        // ------------------------ 设置默认值 ------------------------
        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }


        // ------------------------ handle ------------------------
        PagedGridResult pagedGridResult = itemService.searchItemsByKeywords(keywords, sort, page, pageSize);

        return JsonResult.ok(pagedGridResult);
    }

    /**
     * 通过分类id搜索商品列表
     */
    @ApiOperation(value = "searchItemsByCategoryId", notes = "searchItemsByCategoryId", httpMethod = "GET")
    @GetMapping("/catItems")
    public JsonResult catItems(
            @ApiParam(name = "catId", value = "catId", required = true)
            @RequestParam Integer catId,
            @ApiParam(name = "sort", value = "sort", required = false)
            @RequestParam String sort,
            @ApiParam(name = "page", value = "page", required = false)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize", value = "pageSize", required = false)
            @RequestParam Integer pageSize) {
        if (catId == null) {
            return JsonResult.errorMsg("wrong category id");
        }

        // ------------------------ 设置默认值 ------------------------
        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = COMMON_PAGE_SIZE;
        }

        PagedGridResult pagedGridResult = itemService.searchItemsByThirdCategoryId(catId, sort, page, pageSize);


        return JsonResult.ok(pagedGridResult);
    }


}
