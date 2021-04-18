package com.bjtu226.ebookshop.controller;

import com.alibaba.fastjson.JSONObject;
import com.bjtu226.ebookshop.entity.Book;
import com.bjtu226.ebookshop.mapper.BookMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping
public class BookController {

    @Resource
    private BookMapper bookMapper;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")int id,Model model) {
        int i=bookMapper.deleteBook(id);
        PageHelper.startPage(1, 10);
        PageInfo<Book> pageInfo = new PageInfo<>(bookMapper.selectAll());
        model.addAttribute("pageInfo", pageInfo);
        return "/all_book";
    }

    @GetMapping("/modify/{name}")
    public String modify(@PathVariable("name")String name,Model model){
        Book book=bookMapper.selectOneBookByName(name);

        model.addAttribute("book",book);
        return "/modify";
    }

    @PostMapping("/modify")
    public String submit_modify(@RequestParam("modify_name")String name,
                                @RequestParam("modify_author")String author,
                                @RequestParam("modify_tag")String tag,
                                @RequestParam("modify_price")String price,
                                @RequestParam("modify_file")String file,
                                Model model){
        Book book=new Book();
        book.setName(name);
        book.setPrice(Float.parseFloat(price));
        book.setTag(tag);
        book.setAuthor(author);
        book.setFile(file);
        System.out.println("there has been came");
        bookMapper.updateBookByName(book);
        PageHelper.startPage(1, 10);
        PageInfo<Book> pageInfo = new PageInfo<>(bookMapper.selectAll());
        model.addAttribute("pageInfo", pageInfo);
        return "/all_book";
    }

    @GetMapping("/showFile/{name}")
    @ResponseBody
    public String show_the_file(@PathVariable("name")String name) {
        Book book=bookMapper.selectOneBookByName(name);
        String what=book.getFile();
        System.out.println(what);
        return what;
    }

    @RequestMapping("/addBook")
    public String add(@RequestBody JSONObject jsonObject,Model model) {
        Book book=new Book();
        book.setName((String)jsonObject.get("new_name"));
        book.setPrice(Float.parseFloat((String)jsonObject.get("new_price")));
        book.setAuthor((String)jsonObject.get("new_author"));
        book.setTag((String)jsonObject.get("new_tag"));
        book.setFile((String)jsonObject.get("new_file"));
        bookMapper.insertBook(book);
        PageHelper.startPage(1, 10);
        PageInfo<Book> pageInfo = new PageInfo<>(bookMapper.selectAll());
        model.addAttribute("pageInfo", pageInfo);
        return "/all_book";
    }

    @GetMapping("/modifyBook")
    public String getModifyPage() {
        return "/modify";
    }

    @GetMapping("/getAllBook")
    public String getAllPerson(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Book> list = bookMapper.selectAll();
        PageInfo<Book> pageInfo = new PageInfo<Book>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "/all_book";
    }

}