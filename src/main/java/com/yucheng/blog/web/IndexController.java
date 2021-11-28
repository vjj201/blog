package com.yucheng.blog.web;

import com.yucheng.blog.pojo.Blog;
import com.yucheng.blog.service.BlogService;
import com.yucheng.blog.service.TagService;
import com.yucheng.blog.service.TypeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author YuCheng
 * @date 2021/9/20 - 上午 12:45
 */

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 5, sort = {"updateTime"}) Pageable pageable,
                        Model model) {
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listType(6));
        model.addAttribute("tags", tagService.listTag(10));
        model.addAttribute("recommendBlogs", blogService.listBlog(8));
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable Long id,
                             Model model) {
        Blog blog = null;
        try {
            blog = blogService.getAndConvert(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        model.addAttribute("blog", blog);
        return "blog";
    }

    @GetMapping("/tags")
    public String tags() {
        return "tags";
    }

    @GetMapping("/types")
    public String types() {
        return "types";
    }

    @GetMapping("/archives")
    public String archives() {
        return "archives";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

}
