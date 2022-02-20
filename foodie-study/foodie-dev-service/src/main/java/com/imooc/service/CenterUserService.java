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
     *
     */
    public void updateUserInfo(String userId, CenterUserBO centerUserBO);
}
