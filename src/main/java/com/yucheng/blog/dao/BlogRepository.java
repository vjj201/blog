package com.yucheng.blog.dao;

import com.yucheng.blog.pojo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author YuCheng
 * @date 2021/9/24 - 下午 02:43
 */
public interface BlogRepository extends JpaRepository<Blog, Long> , JpaSpecificationExecutor<Blog> {

}
