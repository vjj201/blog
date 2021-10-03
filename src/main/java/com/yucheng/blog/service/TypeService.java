package com.yucheng.blog.service;

import com.yucheng.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author YuCheng
 * @date 2021/9/22 - 下午 04:40
 */
public interface TypeService {

    //新增分類
    Type saveType(Type type);

    //編號查詢分類
    Type getType(Long id);

    //名稱查詢分類
    Type getTypeByName(String name);

    //顯示分頁類表
    Page<Type> listType(Pageable pageable);

    //查詢全部
    List<Type> listType();

    //更新分頁
    Type updateType(Long id, String name);

    //刪除分類
    void deleteType(Long id);


}
