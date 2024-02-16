package com.funix.PRJ321x_Project2_tamFX27974.service;

import com.funix.PRJ321x_Project2_tamFX27974.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getTop4Categories();

    List<Category> getAllCategories();

    Category get(int id);
}
