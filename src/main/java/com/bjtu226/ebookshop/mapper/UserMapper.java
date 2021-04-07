package com.bjtu226.ebookshop.mapper;

import com.bjtu226.ebookshop.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Insert("insert into " +
            "user(name, password, phone, authority) " +
            "values(#{name}, #{password}, #{phone}, #{authority})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(User user);

    @Delete("delete from " +
            "user " +
            "where id = #{id}")
    int deleteUser(int id);

    @Update("update user " +
            "set " +
            "name = #{name}, " +
            "password = #{password}, " +
            "phone = #{phone}, " +
            "authority = #{authority} " +
            "where id = #{id}")
    int updateUser(User user);

    @Select("select * " +
            "from user " +
            "where name = #{name}")
    @ResultType(User.class)
    User selectUser(String name);
}
