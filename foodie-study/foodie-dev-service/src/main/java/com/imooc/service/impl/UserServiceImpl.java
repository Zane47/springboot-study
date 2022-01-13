package com.imooc.service.impl;
import java.util.Date;

import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


@Service
public class UserServiceImpl implements UserService {

    // 默认头像
    private static final String DEFAULT_USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";


    @Autowired
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUserNameIsExisted(String userName) {
        // 根据条件查询
        Example example = new Example(Users.class);
        Example.Criteria userCriteria = example.createCriteria();
        userCriteria.andEqualTo("username", userName);

        Users users = usersMapper.selectOneByExample(example);
        return null != users;
    }


    /**
     * 创建用户
     *
     * @param userBO 前台传入的业务对象
     *
     * @return Users
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {
        Users user = new Users();
        user.setId("");
        user.setUsername(userBO.getUserName());

        try {
            // 密码使用md5加密
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));
        } catch(Exception e) {
            e.printStackTrace();
        }
        // 默认用户昵称同用户名
        user.setNickname(userBO.getUserName());
        user.setFace(DEFAULT_USER_FACE);
        user.setSex(Sex.secret.type);
        // 设置默认生日
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);

        return user;
    }


}
