package com.nitesh.Service;

import com.nitesh.model.IngredientCategory;
import com.nitesh.model.IngredientsItem;

import java.util.List;

public interface IngredientService {

    public IngredientCategory createIngredientCategory(String name,Long restaurantId) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;

    public IngredientsItem createIngredientsItem(String ingredient_name,Long restaurantId,Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantIngredients(Long id) throws Exception;

    public IngredientsItem updateStock(Long id) throws Exception;


}
