package com.bjtu226.ebookshop.controller;

import com.bjtu226.ebookshop.entity.Book;
import com.bjtu226.ebookshop.entity.User;
import com.bjtu226.ebookshop.mapper.BookMapper;
import com.bjtu226.ebookshop.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    @Resource
    private BookMapper bookMapper;

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {
        User user = userMapper.selectUser(name);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "用户名或密码错误！");
            return "/login";
        }
        session.setAttribute("user", user.getId());
        PageHelper.startPage(1, 10);
        PageInfo<Book> pageInfo = new PageInfo<>(bookMapper.selectAll());
        model.addAttribute("pageInfo", pageInfo);
        return "/all_book_manager";
    }


}
