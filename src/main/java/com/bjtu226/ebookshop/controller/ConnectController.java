package com.bjtu226.ebookshop.controller;

import com.bjtu226.ebookshop.entity.User;
import com.bjtu226.ebookshop.mapper.BookMapper;
import com.bjtu226.ebookshop.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class ConnectController {
    @Resource
    private UserMapper userMapper;

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, HttpSession session, Model model) {
        System.out.println(name+":"+password);

        User user = userMapper.selectUser(name);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }

        session.setAttribute("user", user.getId());

        return "/all_book";
    }
}
