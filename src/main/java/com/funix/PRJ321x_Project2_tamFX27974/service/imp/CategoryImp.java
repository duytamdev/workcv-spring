package com.funix.PRJ321x_Project2_tamFX27974.service.imp;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Category;
import com.funix.PRJ321x_Project2_tamFX27974.repository.CategoryRepository;
import com.funix.PRJ321x_Project2_tamFX27974.service.CategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getTop4Categories() {

        Pageable pageable = PageRequest.of(0, 4);
        List<Object[]> list = categoryRepository.findTopCategoriesWithOrWithoutRecruitments(pageable);
        List<Category> categories = new ArrayList<>();

        for (Object[] objects : list) {
            Category category = new Category();
            category.setName((String) objects[0]);
            Long recruitmentCountLong = (Long) objects[1];
            category.setNumberChoose(recruitmentCountLong.intValue());
            categories.add(category);
        }
        // list object[] -> list category
        return categories;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category get(int id) {
        return categoryRepository.getReferenceById(id);
    }
}
