package com.yucheng.blog.service;

import com.yucheng.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author YuCheng
 * @date 2021/9/23 - 上午 09:16
 */

public interface TagService {

    //新增標籤
    Tag saveTag(Tag tag);

    //依編號搜尋標籤
    Tag getTag(Long id);

    //依名稱搜尋標籤
    Tag getTagByName(String name);

    //顯示標籤列表
    Page<Tag> listTag(Pageable pageable);

    //筆數顯示
    List<Tag> listTag(Integer size);

    //修改標籤
    Tag updateTag(Long id, String name);

    //刪除標籤
    void deleteTag(Long id);

    //獲取標籤列
    List<Tag> listTag();

    //透過編號字串獲取標籤列
    List<Tag> listTag(String ids);

}
