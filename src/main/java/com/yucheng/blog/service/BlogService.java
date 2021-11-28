package com.yucheng.blog.service;

import com.yucheng.blog.pojo.Blog;
import com.yucheng.blog.vo.BlogQuery;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author YuCheng
 * @date 2021/9/23 - 下午 11:54
 */
public interface BlogService {

    //標籤查詢
    Blog getBlog(Long id);

    //根據搜尋選項，列表查詢
    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

    Page<Blog> listBlog(Pageable pageable);

    List<Blog> listBlog(Integer size);

    //新增
    Blog saveBlog(Blog blog);

    //修改
    Blog updateBlog(Long id, Blog blog);

    //刪除
    void deleteBlog(Long id);

    //查詢並轉換
    Blog getAndConvert(Long id) throws NotFoundException;

}
