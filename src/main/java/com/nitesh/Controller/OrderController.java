package com.nitesh.Controller;

import com.nitesh.Service.OrderService;
import com.nitesh.Service.PaymentService;
import com.nitesh.Service.UserService;
import com.nitesh.model.CartItem;
import com.nitesh.model.Order;
import com.nitesh.model.User;
import com.nitesh.request.AddCartItemRequest;
import com.nitesh.request.OrderRequest;
import com.nitesh.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
//    @CrossOrigin(origins="*")
    public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderRequest req,
                                                       @RequestHeader("Authorization") String jwt)
                                             throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.creatOrder(req,user);
        PaymentResponse response = paymentService.createPaymentLink(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory(@RequestHeader("Authorization") String jwt)
                                             throws Exception {
        System.out.println(1);
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getUserOrders(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
