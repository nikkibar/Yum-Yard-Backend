package com.nitesh.Controller;

import com.nitesh.Service.FoodService;
import com.nitesh.Service.RestaurantService;
import com.nitesh.Service.UserService;
import com.nitesh.model.Food;
import com.nitesh.model.Restaurant;
import com.nitesh.model.User;
import com.nitesh.repository.FoodRepository;
import com.nitesh.request.CreateFoodRequest;
import com.nitesh.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private FoodRepository foodRepository;

    @PostMapping
//    @CrossOrigin(origins="*")
    public ResponseEntity<Food> createFood(
            @RequestBody CreateFoodRequest req,
            @RequestHeader("Authorization") String jwt)  throws Exception
    {

        User user = userService.findUserByJwtToken(jwt);
        Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());

        Food food = foodService.createFood(req, req.getCategory(), restaurant);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
//    @CrossOrigin(origins="*")
    public ResponseEntity<MessageResponse> deleteFood(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt)  throws Exception
    {

        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Food Deleted Successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
//    @CrossOrigin(origins="*")
    public ResponseEntity<Food> updateFoodAvailabilityStatus(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt)  throws Exception
    {

        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.updateAvailibilityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }



}
