package com.imooc.service.impl;

import com.imooc.mapper.CarouselMapper;
import com.imooc.pojo.Carousel;
import com.imooc.service.CarouselService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {


    @Autowired
    private CarouselMapper carouselMapper;


    /**
     * 查询所有可显示的轮播图
     *
     * @param isShow
     * @return
     */
    @Override
    public List<Carousel> queryAllValidCarousel(Integer isShow) {
        Example example = new Example(Carousel.class);
        // sort顺序属性, 倒序输出
        example.orderBy("sort").desc();

        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", isShow);

        return carouselMapper.selectByExample(example);
    }
}
