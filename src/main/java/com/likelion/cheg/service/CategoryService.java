package com.likelion.cheg.service;

import com.likelion.cheg.domain.category.Category;
import com.likelion.cheg.domain.category.CategoryRepository;
import com.likelion.cheg.web.dto.category.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDto> makeResponseDto(List<Category> categoryList){
        List<CategoryResponseDto> categoryListDtos = categoryList.stream()
                .map(category -> new CategoryResponseDto(category.getName()))
                .collect(Collectors.toList());
        return categoryListDtos;
    }


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
    public Category saveCategory(String categoryName){
        Category category = Category.createCategory(categoryName);
        categoryRepository.save(category);
        return category;
    }

}
