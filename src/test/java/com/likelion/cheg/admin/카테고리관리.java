package com.likelion.cheg.admin;

import com.likelion.cheg.CommonMethod;
import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class 카테고리관리 {

    @Autowired
    CategoryService categoryService;

    @Test
    public void 카테고리_등록(){
        String categoryName = "NEW카테고리2123123";
        Category category = categoryService.saveCategory(categoryName);
        assertEquals("카테고리 이름 확인",category.getName(),categoryName);
    }
}
