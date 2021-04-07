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
import java.util.List;

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
    public String login(@RequestParam(value = "name") String name, @RequestParam(value = "password") String password, HttpSession session, Model model) {
        System.out.println(name+":"+password);

        User user = userMapper.selectUser(name);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "用户名或密码错误！");
            return "login";
        }
        session.setAttribute("user", user.getId());
        PageHelper.startPage(1, 10);
        PageInfo<Book> pageInfo = new PageInfo<>(bookMapper.selectAll());
        model.addAttribute("pageInfo", pageInfo);
        return "/all_book";
    }

    @GetMapping("/getAllBook")
    public String getAllPerson(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Book> list = bookMapper.selectAll();
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "/all_book";
    }
}
