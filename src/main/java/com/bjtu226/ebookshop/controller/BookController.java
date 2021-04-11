package com.bjtu226.ebookshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.bjtu226.ebookshop.entity.Book;
import com.bjtu226.ebookshop.mapper.BookMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
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


    @GetMapping("/show_file/{name}")
    @ResponseBody
    public String show_the_file(@PathVariable("name")String name) {
        Book book=bookMapper.selectoneBookByName(name);
        String what=book.getFile();
        System.out.println(what);
        return what;
    }

    @PostMapping("/addBook")
    public void addBook(HttpServletResponse response) throws Exception {

        response.sendRedirect("/getAllBook");
    }

    @RequestMapping("/AddDataToServer")
    @ResponseBody
    public void add(@RequestBody JSONObject jsonObject,Model model) throws Exception {


        Book book=new Book();
        book.setName((String)jsonObject.get("new_name"));
        book.setPrice(Float.parseFloat((String)jsonObject.get("new_price")));
        book.setAuthor((String)jsonObject.get("new_author"));
        book.setTag((String)jsonObject.get("new_tag"));
        book.setFile((String)jsonObject.get("new_file"));
        bookMapper.insertBook(book);

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
    public String getAllPerson(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Book> list = bookMapper.selectAll();
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "/all_book_manager";
    }

    @GetMapping("/searchBook")
    public String searchBook() {
        return "/search_list";
    }






}