package com.imooc.service.impl;

import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.CenterUserBO;
import com.imooc.service.CenterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CenterUserServiceImpl implements CenterUserService {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 根据userId查询User信息
     *
     * @param userId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserInfoById(String userId) {
        return usersMapper.selectByPrimaryKey(userId);
    }


    /**
     *
     * @param userId
     * @param centerUserBO
     */
    @Override
    public void updateUserInfo(String userId, CenterUserBO centerUserBO) {

    }
}
