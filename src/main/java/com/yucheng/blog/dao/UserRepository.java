package com.yucheng.blog.dao;

import com.yucheng.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author YuCheng
 * @date 2021/9/21 - 下午 11:14
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
}
