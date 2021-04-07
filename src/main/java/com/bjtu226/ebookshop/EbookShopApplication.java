package com.bjtu226.ebookshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.bjtu226.ebookshop.mapper")
@SpringBootApplication
public class EbookShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbookShopApplication.class, args);
    }

}
