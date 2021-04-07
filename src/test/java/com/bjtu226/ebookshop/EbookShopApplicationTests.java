package com.bjtu226.ebookshop;

import com.bjtu226.ebookshop.mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EbookShopApplicationTests {

    @Autowired
    BookMapper bookMapper;

    @Test
    void testFuzzyQuery () {
        System.out.println(bookMapper.selectBookByName("1"));
    }
}
