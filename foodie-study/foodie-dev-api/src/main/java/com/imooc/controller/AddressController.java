package com.imooc.controller;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import com.imooc.service.impl.AddressServiceImpl;
import com.imooc.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户在确认订单页面，可以针对收货地址做如下操作：
 * 1. 查询用户的所有收货地址列表
 * 2. 新增收货地址
 * 3. 删除收货地址
 * 4. 修改收货地址
 * 5. 设置默认地址
 */
@Api(value = "address", tags = {"address related api"})
@RequestMapping("address")
@RestController
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;


    /**
     * 根据用户id查询所有收货地址列表
     */
    @ApiOperation(value = "queryAddressByUserId", notes = "根据用户id查询收货地址列表", httpMethod = "POST")
    @PostMapping("list")
    public JsonResult list(@RequestParam String userId) {
        if (StringUtils.isBlank(userId)) {
            return JsonResult.errorMsg("wrong user id");
        }

        List<UserAddress> userAddressList = addressService.queryAll(userId);

        return JsonResult.ok(userAddressList);
    }


    /**
     * 用户新增地址
     */
    @ApiOperation(value = "addNewAddress", notes = "用户新增地址", httpMethod = "POST")
    @PostMapping("add")
    public JsonResult addNewAddress(@RequestBody AddressBO addressBo) {
        // ------------------------ 校验前台传来的信息 ------------------------





        // ------------------------ 新增地址 ------------------------

        return JsonResult.ok();
    }


    /**
     * 用户修改地址
     */
    @ApiOperation(value = "updateAddress", notes = "update address", httpMethod = "POST")
    @PostMapping("update")
    public JsonResult updateAddress(@RequestBody AddressBO addressBO) {
        


        return JsonResult.ok();
    }


    /**
     * 用户删除地址
     */
    @ApiOperation(value = "deleteAddress", notes = "delete address", httpMethod = "POST")
    @PostMapping("update")
    public JsonResult deleteAddress(@RequestParam String userId,
                                    @RequestParam String addressId) {



        return JsonResult.ok();
    }


    /**
     * 设置默认地址
     */

    @PostMapping("setDefalut")
    public JsonResult setDefaultAddress(@RequestParam String userId,
                                        @RequestParam String addressId) {



        return JsonResult.ok();
    }


}
