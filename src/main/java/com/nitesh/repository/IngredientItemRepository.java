package com.nitesh.repository;

import com.nitesh.model.IngredientsItem;
import com.nitesh.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngredientsItem,Long> {

    public List<IngredientsItem> findByRestaurantId(Long id);

}
