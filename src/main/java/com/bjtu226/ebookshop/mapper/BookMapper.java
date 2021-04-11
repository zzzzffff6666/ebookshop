package com.bjtu226.ebookshop.mapper;

import com.bjtu226.ebookshop.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {

    @Insert("insert into " +
            "book(name, price, tag, author, file) " +
            "values(#{name}, #{price}, #{tag}, #{author}, #{file})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBook(Book book);



    @Delete("delete from " +
            "book " +
            "where id = #{id}")
    int deleteBook(int id);

    @Update("update book " +
            "set " +
            "name = #{name}, " +
            "price = #{price}, " +
            "tag = #{tag}, " +
            "author = #{author}, " +
            "file = #{file} " +
            "where id = #{id}")
    int updateBook(Book book);

    @Select("select * " +
            "from book " +
            "where id = #{id}")
    Book selectBookByID(int id);

    @Select("select * " +
            "from book")
    List<Book> selectAll();

    @Select("select * " +
            "from book " +
            "where name = #{name}")
    Book selectoneBookByName(String name);

    @Select("select * " +
            "from book " +
            "where name like '%${name}%'")
    List<Book> selectBookByName(String name);

    @Select("select * " +
            "from book " +
            "where tag like '%${tag}%'")
    List<Book> selectBookByTag(String tag);

    @Select("select * " +
            "from book " +
            "where author like '%${author}%'")
    List<Book> selectBookByAuthor(String author);
}
