package com.yucheng.blog.dao;

import com.yucheng.blog.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author YuCheng
 * @date 2021/9/23 - 上午 09:13
 */

public interface TagRepository  extends JpaRepository<Tag, Long> {

    Tag findByName(String name);
}
