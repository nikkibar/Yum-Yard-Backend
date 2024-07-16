package com.nitesh.Service;

import com.nitesh.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name, Long user_id) throws Exception;

    public List<Category> findCategoryByRestaurantId (Long restaurant_id) throws Exception;

    public Category findCategoryById(Long category_id) throws Exception;

}
