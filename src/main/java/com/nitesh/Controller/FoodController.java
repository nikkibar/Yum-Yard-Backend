package com.nitesh.Controller;

import com.nitesh.Service.FoodService;
import com.nitesh.Service.RestaurantService;
import com.nitesh.Service.UserService;
import com.nitesh.model.Food;
import com.nitesh.model.User;
import com.nitesh.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/food")
@CrossOrigin(origins = "http://localhost:3000")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private FoodRepository foodRepository;

    @GetMapping("/restaurant/{restaurantId}")
//    @CrossOrigin(origins="*")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam(required = true) Boolean vegetarian,
            @RequestParam(required = true) Boolean seasonal,
            @RequestParam(required = true) Boolean nonVeg,
            @PathVariable Long restaurantId,
            @RequestParam(required = false) String food_category,
            @RequestHeader("Authorization") String jwt)  throws Exception
    {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodService.getRestaurantFood(restaurantId, vegetarian, seasonal, nonVeg, food_category);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
}
