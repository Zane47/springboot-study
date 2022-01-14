package com.imooc.controller;


import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.service.CarouselService;
import com.imooc.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "index", tags = {"index apis"})
@RequestMapping("index")
@RestController
public class IndexController {


    @Autowired
    private CarouselService carouselService;

    @ApiOperation(value = "getAllValidCarousel", notes = "getAllValidCarousel", httpMethod = "GET")
    @GetMapping("/carousel")
    public JsonResult getAllValidCarousel() {
        List<Carousel> carousels = carouselService.queryAllValidCarousel(YesOrNo.YES.type);

        return JsonResult.ok(carousels);
    }


}
