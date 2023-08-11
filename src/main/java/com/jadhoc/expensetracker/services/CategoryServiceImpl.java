package com.jadhoc.expensetracker.services;

import com.jadhoc.expensetracker.domain.Category;
import com.jadhoc.expensetracker.exceptions.EtBadRequestException;
import com.jadhoc.expensetracker.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> fetchAllCategories(Integer userId) {
        return null;
    }

    @Override
    public Category fetchCategoryById(Integer userId, Integer categoryId) throws EtBadRequestException {
        return null;
    }

    @Override
    public Category addCategory(Integer userId, String title, String description) throws EtBadRequestException {
        Integer categoryId = categoryRepository.create(userId, title, description);

        System.out.println("MYLOG: " + categoryId  + " | " + userId + " | " + title + " | " + description);

        //return categoryRepository.findById(userId, categoryId);

        return new Category(categoryId, userId, title, description, 0.0);
    }

    @Override
    public void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {

    }

    @Override
    public void removeCategoryWithAllTransactions(Integer userId, Integer categoryId) throws EtBadRequestException {

    }
}
