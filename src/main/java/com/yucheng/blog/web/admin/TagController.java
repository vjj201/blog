package com.yucheng.blog.web.admin;

import com.yucheng.blog.pojo.Tag;
import com.yucheng.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author YuCheng
 * @date 2021/9/23 - 上午 09:29
 */

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //跳轉標籤頁面
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "/admin/tags";
    }

    //返回標籤新增頁面
    @GetMapping("/tags/input")
    public String tagsInputPage(Model model) {
        model.addAttribute("tag", new Tag());
        return "/admin/tags-input";
    }

    //新增標籤
    @PostMapping("/tags")
    public String insert(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag t = tagService.getTagByName(tag.getName());

        if(t != null) {
            result.rejectValue("name", "nameError", "標籤名稱不能重複");
        }

        if(result.hasErrors()) {
            return "/admin/tags-input";
        }

        try {
            t = tagService.saveTag(tag);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(t != null) {
            attributes.addFlashAttribute("message","新增成功");
        } else {
            attributes.addFlashAttribute("message", "新增失敗");
        }
        return "redirect:/admin/tags";
    }

    //跳轉更新標籤頁面
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTag(id));
        return "/admin/tags-input";
    }

    //更新標籤
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag,
                           BindingResult result,
                           @PathVariable Long id,
                           RedirectAttributes attributes) {

        Tag t = tagService.getTagByName(tag.getName());

        if(t != null) {
            result.rejectValue("name", "nameError", "標籤名稱不能重複");
        }

        if(result.hasErrors()) {
            return "/admin/tags-input";
        }

        try {
            t = tagService.updateTag(id, tag.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(t != null) {
            attributes.addFlashAttribute("message", "更新成功");
        } else {
            attributes.addFlashAttribute("message", "新增失敗");
        }

        return "redirect:/admin/tags";
    }

    //刪除標籤
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "刪除成功");
        return "redirect:/admin/tags";
    }


}
