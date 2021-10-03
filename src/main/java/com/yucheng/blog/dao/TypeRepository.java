package com.yucheng.blog.dao;

import com.yucheng.blog.pojo.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author YuCheng
 * @date 2021/9/22 - 下午 04:48
 */
public interface TypeRepository  extends JpaRepository<Type, Long> {

    Type findByName(String name);
}
