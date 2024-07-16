package com.nitesh.Service;

import com.nitesh.model.Category;
import com.nitesh.model.Food;
import com.nitesh.model.Restaurant;
import com.nitesh.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId, Boolean isVegetarian,
                                        Boolean isNonVeg, Boolean isSeasonal,
                                        String foodCategory);

    public List<Food> searchFoods(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public  Food updateAvailibilityStatus(Long foodId) throws Exception;
}
