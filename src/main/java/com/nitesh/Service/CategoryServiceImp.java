package com.nitesh.Service;

import com.nitesh.model.Category;
import com.nitesh.model.Restaurant;
import com.nitesh.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long user_id) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(user_id);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return  categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long restaurant_id) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurant_id);
        return categoryRepository.findByRestaurantId(restaurant_id);
    }

    @Override
    public Category findCategoryById(Long category_id) throws Exception {
        Optional<Category> category = categoryRepository.findById(category_id);

        if(category.isEmpty()){
            throw  new Exception("Category not found");
        }
        return category.get();
    }
}
