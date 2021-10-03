package com.yucheng.blog.service;

import com.yucheng.blog.dao.UserRepository;
import com.yucheng.blog.pojo.User;
import com.yucheng.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author YuCheng
 * @date 2021/9/21 - 下午 11:08
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    //確認帳號密碼
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
