package com.nitesh.repository;

import com.nitesh.model.Category;
import com.nitesh.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    public List<Category> findByRestaurantId(Long restaurant_id);
}
