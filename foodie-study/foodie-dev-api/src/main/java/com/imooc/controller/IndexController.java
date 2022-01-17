package com.imooc.controller;


import com.imooc.aspect.ServiceLogAspect;
import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.utils.JsonResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "index", tags = {"index apis"})
@RequestMapping("index")
@RestController
public class IndexController {

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);


    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 首页轮播图
     *
     * @return
     */
    @ApiOperation(value = "getAllValidCarousel", notes = "getAllValidCarousel", httpMethod = "GET")
    @GetMapping("/carousel")
    public JsonResult getAllValidCarousel() {
        List<Carousel> carousels = carouselService.queryAllValidCarousel(YesOrNo.YES.type);

        return JsonResult.ok(carousels);
    }


    // ------------------------ 首页分类展示需求 ------------------------
    /**
     * 1. 第一次刷新主页查询大分类，渲染展示到首页
     * 2. 如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载（懒加载）
     */

    /**
     * 查询所有的根目录, type = 1
     */
    @ApiOperation(value = "getAllRootCategories", notes = "getAllRootCategories", httpMethod = "GET")
    @GetMapping("cats")
    public JsonResult getAllRootCategories() {
        List<Category> categories = categoryService.queryAllRootCategories();

        return JsonResult.ok(categories);
    }


    /**
     * 获取商品子分类, 根据根目录
     */
    @ApiOperation(value = "getSubCategory", notes = "getSubCategoriesByRootId", httpMethod = "GET")
    @GetMapping("/subCat/{rootCategoryId}")
    public JsonResult getSubCategoriesByRootId(
            @ApiParam(name = "rootCategoryId", value = "RootCategoryId", required = true)
            @PathVariable Integer rootCategoryId) {
        if (rootCategoryId == null) {
            return JsonResult.errorMsg("wrong category root id");
        }

        List<CategoryVO> subCategoryList = categoryService.getSubCategoryList(rootCategoryId);

        return JsonResult.ok(subCategoryList);
    }


    /**
     * 查询每个一级分类下的最新6条商品数据
     */
    @ApiOperation(value = "getSixNewItems", notes = "get 6 items based on the rootCateGoryId", httpMethod = "GET")
    @GetMapping("/sixNewItems/{rootCategoryId}")
    public JsonResult getSixNewItems(
            @ApiParam(name = "rootCategoryId", value = "RootCategoryId", required = true)
            @PathVariable Integer rootCategoryId) {

        if (rootCategoryId == null) {
            return JsonResult.errorMsg("wrong category root id");
        }

        List<NewItemsVO> sixNewItems = categoryService.getSixNewItemsLazy(rootCategoryId);
        LOGGER.info(JsonUtils.objectToJson(sixNewItems));
        return JsonResult.ok(sixNewItems);
    }

}
