package com.nuc.service;

import com.nuc.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuc.utils.Result;

/**
* @author wj183
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-04-08 16:52:37
*/
public interface UserService extends IService<User> {

    /**
     * 登录业务
     * @param user
     * @return
     */
    Result login(User user);

    /**
     * 根据token获取用户数据
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    /**
     * 检查账号是否可用
     * @param username
     * @return
     */
    Result checkUserName(String username);

    /**
     * 实现注册业务
     * @param user
     * @return
     */
    Result regist(User user);
}
