package com.imooc.service.impl;

import com.imooc.enums.YesOrNo;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private Sid sid;


    /**
     * 根据用户id查询所有收货地址列表
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAll(String userId) {

        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);

        return userAddressMapper.select(userAddress);
    }

    /**
     * 新增用户地址
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addNewUserAddress(AddressBO addressBO) {
        // 1.如果当前用户不存在地址, 那么新增的则为'默认地址'
        int isDefault = 0;
        List<UserAddress> userAddressList = this.queryAll(addressBO.getUserId());
        if (userAddressList == null || userAddressList.isEmpty()) {
            isDefault = 0;
        }
        String addressId = sid.nextShort();

        // 2. 保存地址到数据库
        UserAddress newAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, newAddress);

        newAddress.setId(addressId);
        newAddress.setIsDefault(isDefault);
        newAddress.setCreatedTime(new Date());
        newAddress.setUpdatedTime(new Date());

        userAddressMapper.insert(newAddress);
    }


    /**
     * update address
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserAddress(AddressBO addressBO) {
        String addressId = addressBO.getAddressId();

        UserAddress pendingAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO, pendingAddress);

        pendingAddress.setId(addressId);
        pendingAddress.setUpdatedTime(new Date());

        userAddressMapper.updateByPrimaryKeySelective(pendingAddress);
    }


    /**
     * delete address
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setId(addressId);
        userAddressMapper.delete(userAddress);
    }


    /**
     * set address to default
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void setDefaultAddress(String userId, String addressId) {
        // 1.默认地址设置非默认: 先查询默认地址, 然后在设置为非默认地址
        UserAddress old = new UserAddress();
        old.setUserId(userId);
        old.setIsDefault(YesOrNo.YES.type);

        // 这样子可以避免当用户没有默认地址时可能查询为null
        List<UserAddress> oldAddresses = userAddressMapper.select(old);
        for (UserAddress oldAddress : oldAddresses) {
            oldAddress.setIsDefault(YesOrNo.NO.type);
            userAddressMapper.updateByPrimaryKeySelective(oldAddress);
        }

        // 2.设置新地址为默认地址
        UserAddress now = new UserAddress();
        now.setUserId(userId);
        now.setId(addressId);
        now.setIsDefault(YesOrNo.YES.type);
        userAddressMapper.updateByPrimaryKeySelective(now);
    }


    /**
     *  query specific address info by userId and addressId
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserAddress querySpecificAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setId(addressId);
        return userAddressMapper.selectOne(userAddress);
    }


}
