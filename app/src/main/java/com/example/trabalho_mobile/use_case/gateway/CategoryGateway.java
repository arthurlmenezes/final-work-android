package com.example.trabalho_mobile.use_case.gateway;

import com.example.trabalho_mobile.entity.Category;
import com.example.trabalho_mobile.entity.User;
import com.example.trabalho_mobile.external.database.model.CategoryData;

import java.util.List;

public interface CategoryGateway {
    CategoryData add(Category category, User user);
    List<CategoryData> findAll(User user);
    CategoryData findById(String id, User user);
}
