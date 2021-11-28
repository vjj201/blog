package com.yucheng.blog.dao;

import com.yucheng.blog.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author YuCheng
 * @date 2021/9/22 - 下午 04:48
 */
public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findByName(String name);

    @Query("SELECT t FROM Type t")
    List<Type> findTop(Pageable pageable);
}
