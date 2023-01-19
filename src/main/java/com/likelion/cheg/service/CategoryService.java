package com.likelion.cheg.service;

import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public List<Category> loadAllCateogory(){
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    @Transactional
    public Category findOne(String name){
        Category category = categoryRepository.findByCategoryName(name);
        return category;
    }

    @Transactional
    public Category saveOne(String name){
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
        return category;
    }

}
