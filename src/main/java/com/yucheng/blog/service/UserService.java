package com.yucheng.blog.service;

import com.yucheng.blog.pojo.User;

/**
 * @author YuCheng
 * @date 2021/9/21 - 下午 03:53
 */
public interface UserService {

    User checkUser(String username, String password);
}
