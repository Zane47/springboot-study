package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.CenterUserBO;

public interface CenterUserService {

    /**
     * 根据userId查询user信息
     *
     * @param userId
     * @return
     */
    public Users queryUserInfoById(String userId);

    /**
     * 修改用户信息
     */
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO);
}
