package com.jadhoc.expensetracker.repositories;

import com.jadhoc.expensetracker.domain.Category;
import com.jadhoc.expensetracker.exceptions.EtBadRequestException;
import com.jadhoc.expensetracker.exceptions.EtResourceNotFoundException;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAll(Integer userId) throws EtResourceNotFoundException;

    Category findById(Integer userId, Integer categoryId) throws EtResourceNotFoundException;

    Integer create(Integer userId, String title, String description) throws EtBadRequestException;

    void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;

    void removeById(Integer userId, Integer categoryId);
}
