package com.bjtu226.ebookshop.controller;

import com.bjtu226.ebookshop.entity.Book;
import com.bjtu226.ebookshop.mapper.BookMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping
public class BookController {

    @Resource
    private BookMapper bookMapper;

    @GetMapping("/addBook")
    public String getAddPage() {
        return "/add";
    }

    @PostMapping("/addBook")
    public void addBook(HttpServletResponse response) throws Exception {

        response.sendRedirect("/getAllBook");
    }

    @GetMapping("/modifyBook")
    public String getModifyPage() {
        return "/modify";
    }

    @PostMapping("/modifyBook")
    public void modifyBook(HttpServletResponse response) throws Exception {

        response.sendRedirect("/getAllBook");
    }

    @PostMapping("/deleteBook")
    public void deleteBook(HttpServletResponse response) throws Exception {

        response.sendRedirect("/getAllBook");
    }

    @GetMapping("/getAllBook")
    public String getAllPerson(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Book> list = bookMapper.selectAll();
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "/all_book";
    }

    @GetMapping("/searchBook")
    public String searchBook() {
        return "/search_list";
    }
}
