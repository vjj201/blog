package com.yucheng.blog.web.admin;


import com.yucheng.blog.pojo.User;
import com.yucheng.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author YuCheng
 * @date 2021/9/21 - 下午 11:28
 */

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    //跳轉登入畫面
    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    //登入驗證
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {

        User user = userService.checkUser(username, password);

        if(user != null) {
            //密碼清空不要存入會話中
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            //設置重新導向的快閃參數
            attributes.addFlashAttribute("message", "用戶名或密碼錯誤");
            return "redirect:/admin";
        }
    }

    //登出
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        //移除會話
        session.removeAttribute("user");
        //重新導向
        return "redirect:/admin";

    }


}
