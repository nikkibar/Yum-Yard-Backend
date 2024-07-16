package com.nitesh.Controller;

import com.nitesh.Service.OrderService;
import com.nitesh.Service.RestaurantService;
import com.nitesh.model.Order;
import com.nitesh.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/order/restaurant/{restaurant_id}")
//    @CrossOrigin(origins="*")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable Long restaurant_id,
                                                      @RequestParam(required = false) String order_status)
            throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurant_id);
        List<Order> orders = orderService.getRestaurantOrders(restaurant.getId(),order_status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{order_id}/{order_status}")
//    @CrossOrigin(origins="*")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long order_id,
                                                       @PathVariable String order_status)
            throws Exception {
        Order order = orderService.updateOrderStatus(order_id,order_status);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
