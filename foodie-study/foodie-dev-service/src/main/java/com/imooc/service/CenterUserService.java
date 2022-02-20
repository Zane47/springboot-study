package com.imooc.service;

import com.imooc.pojo.Users;

public interface CenterUserService {

    /**
     * 根据userId查询user信息
     *
     * @param userId
     * @return
     */
    public Users queryUserInfoById(String userId);


}
