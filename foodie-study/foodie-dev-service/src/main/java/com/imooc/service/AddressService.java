package com.imooc.service;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface AddressService {

    /**
     * 根据用户id查询用户的收货地址列表
     */
    public List<UserAddress> queryAll(String userId);


    /**
     * 新增地址
     */
    public void addNewUserAddress(AddressBO addressBO);

    /**
     * update address
     */
    public void updateUserAddress(AddressBO addressBO);

    /**
     * delete address
     */
    public void deleteUserAddress(String userId, String addressId);


    /**
     * set default address
     */
    public void setDefaultAddress(String userId, String addressId);

    /**
     * query specific address info by userId and addressId
     */
    public UserAddress querySpecificAddress(String userId, String addressId);

}
