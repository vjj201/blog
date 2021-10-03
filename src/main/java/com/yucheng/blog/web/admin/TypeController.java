package com.yucheng.blog.web.admin;

import com.yucheng.blog.pojo.Type;
import com.yucheng.blog.service.TypeService;
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
 * @date 2021/9/22 - 下午 05:13
 */

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    //開啟後分頁列表顯示
    @GetMapping("/types")
    public String types(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {

        model.addAttribute("page", typeService.listType(pageable));
        return "/admin/types";
    }

    //返回新增分類頁
    @GetMapping("/types/input")
    public String typesInput(Model model) {
        model.addAttribute("type", new Type());
        return "/admin/types-input";
    }

    //新增
    @PostMapping("/types")
    public String insert(@Valid Type type, BindingResult result, RedirectAttributes attributes) {

        Type t = typeService.getTypeByName(type.getName());

        if(t != null) {
            result.rejectValue("name","nameError","分類名稱不能重複新增");
        }

        if(result.hasErrors()) {
            return "/admin/types-input";
        }

        try {
            t = typeService.saveType(type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(t != null) {
            attributes.addFlashAttribute("message", "新增成功");
        } else {
            attributes.addFlashAttribute("message", "新增失敗");
        }

        return "redirect:/admin/types";
    }

    //跳轉更新頁
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "/admin/types-input";
    }

    //更新
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type,
                           BindingResult result,
                           @PathVariable Long id,
                           RedirectAttributes attributes) {

        Type t = typeService.getTypeByName(type.getName());

        if(t != null) {
            result.rejectValue("name", "nameError", "分類名稱不能重複");
        }

        if(result.hasErrors()) {
            return "/admin/types-input";
        }

        t = typeService.updateType(id, type.getName());

        if(t == null) {
            attributes.addFlashAttribute("message", "更新失敗");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }

        return "redirect:/admin/types";
    }

    //刪除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "刪除成功");
        return "redirect:/admin/types";
    }


}
