package com.yucheng.blog.dao;

import com.yucheng.blog.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlogId(Long BlogId);
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
