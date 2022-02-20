package com.imooc.controller.shop;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import com.imooc.utils.JsonResult;
import com.imooc.utils.MobileEmailUtils;
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
@RequestMapping("/address")
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;


    /**
     * 根据用户id查询所有收货地址列表
     */
    @ApiOperation(value = "queryAddressByUserId", notes = "根据用户id查询收货地址列表", httpMethod = "POST")
    @PostMapping("/list")
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
    @PostMapping("/add")
    public JsonResult addNewAddress(@RequestBody AddressBO addressBO) {
        // ------------------------ 校验前台传来的信息 ------------------------
        JsonResult checkResult = checkAddress(addressBO);
        if (checkResult.getStatus() != 200) {
            return checkResult;
        }


        // ------------------------ 新增地址 ------------------------
        addressService.addNewUserAddress(addressBO);

        return JsonResult.ok();
    }


    /**
     * 用户修改地址
     */
    @ApiOperation(value = "updateAddress", notes = "update address", httpMethod = "POST")
    @PostMapping("/update")
    public JsonResult updateAddress(@RequestBody AddressBO addressBO) {
        if (StringUtils.isBlank(addressBO.getAddressId())) {
            return JsonResult.errorMsg("wrong address id");
        }
        JsonResult checkResult = checkAddress(addressBO);
        if (checkResult.getStatus() != 200) {
            return checkResult;
        }

        addressService.updateUserAddress(addressBO);

        return JsonResult.ok();
    }


    /**
     * 用户删除地址
     */
    @ApiOperation(value = "deleteAddress", notes = "delete address", httpMethod = "POST")
    @PostMapping("/delete")
    public JsonResult deleteAddress(@RequestParam String userId,
                                    @RequestParam String addressId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)) {
            return JsonResult.errorMsg("wrong param");
        }

        addressService.deleteUserAddress(userId, addressId);

        return JsonResult.ok();
    }


    /**
     * 设置默认地址
     */

    @PostMapping("/setDefault")
    public JsonResult setDefaultAddress(@RequestParam String userId,
                                        @RequestParam String addressId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(addressId)) {
            return JsonResult.errorMsg("wrong param");
        }

        addressService.setDefaultAddress(userId, addressId);

        return JsonResult.ok();
    }


    /**
     * 校验前台传过来的AddressBO
     */
    private JsonResult checkAddress(AddressBO addressBO) {
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return JsonResult.errorMsg("收货人不能为空");
        }
        if (receiver.length() > 12) {
            return JsonResult.errorMsg("收货人姓名不能太长");
        }


        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return JsonResult.errorMsg("收货人手机号不能为空");
        }
        if (mobile.length() != 11) {
            return JsonResult.errorMsg("收货人手机号长度不正确");
        }
        if (!MobileEmailUtils.checkMobileIsOk(mobile)) {
            return JsonResult.errorMsg("收货人手机号格式不正确");
        }


        if (StringUtils.isBlank(addressBO.getProvince()) ||
                StringUtils.isBlank(addressBO.getCity()) ||
                StringUtils.isBlank(addressBO.getDistrict()) ||
                StringUtils.isBlank(addressBO.getDetail())) {
            return JsonResult.errorMsg("收货地址信息不能为空");
        }

        return JsonResult.ok();
    }

}
