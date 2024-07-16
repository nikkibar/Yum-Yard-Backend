package com.nitesh.Service;

import com.nitesh.dto.RestaurantDto;
import com.nitesh.model.Restaurant;
import com.nitesh.model.User;
import com.nitesh.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long  restaurantId, CreateRestaurantRequest updateRestaurantRequest) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurant(String query);

    public Restaurant findRestaurantById(Long restaurantId) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

//    public RestaurantDto removeFromFavorites(Long restaurantId, User user) throws Exception;
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;
}

