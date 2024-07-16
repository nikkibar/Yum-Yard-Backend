package com.nitesh.Service;

import com.nitesh.model.IngredientCategory;
import com.nitesh.model.IngredientsItem;
import com.nitesh.model.Restaurant;
import com.nitesh.repository.IngredientCategoryRepository;
import com.nitesh.repository.IngredientItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp implements IngredientService {

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);

        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {

        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);

        if(opt.isEmpty()){
            throw new Exception("Ingredient Category not found");
        }
        return opt.get();

    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientsItem(String ingredient_name, Long restaurantId, Long categoryId) throws Exception {

        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientsItem item = new IngredientsItem();
        item.setRestaurant(restaurant);
        item.setCategory(category);
        item.setName(ingredient_name);

        IngredientsItem ingredientsItem = ingredientItemRepository.save(item);
        category.getIngredients().add(ingredientsItem);
        return ingredientsItem;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientItemRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {

        Optional<IngredientsItem> optItem = ingredientItemRepository.findById(id);

        if(optItem.isEmpty()){
            throw new Exception("Ingredient Item not found");
        }

        IngredientsItem item = optItem.get();
        item.setInStock(!item.isInStock());

        return ingredientItemRepository.save(item);
    }
}
