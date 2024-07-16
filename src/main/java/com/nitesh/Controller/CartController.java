package com.nitesh.Controller;

import com.nitesh.Service.CartService;
import com.nitesh.Service.UserService;
import com.nitesh.model.Cart;
import com.nitesh.model.CartItem;
import com.nitesh.model.User;
import com.nitesh.repository.UserRepository;
import com.nitesh.request.AddCartItemRequest;
import com.nitesh.request.UpdateCartItemRequest;
import com.nitesh.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @PutMapping("/cart/add")
//    @CrossOrigin(origins="*")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
                                                  @RequestHeader("Authorization") String jwt)
                                                    throws Exception {

        CartItem cartItem = cartService.addItemToCart(req,jwt);
        return new ResponseEntity<>(cartItem,HttpStatus.CREATED);
    }

    @PutMapping("/cart-item/update")
//    @CrossOrigin(origins="*")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req)
                                                            throws Exception {
        Long id = req.getCartItemId();
        int quantity = req.getQuantity();
        CartItem cartItem = cartService.updateCartItemQuantity(id,quantity);
        return new ResponseEntity<>(cartItem,HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
//    @CrossOrigin(origins="*")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                               @RequestHeader("Authorization") String jwt)
                                               throws Exception {

        Cart cart = cartService.removeItemFromCart(id,jwt);
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @PutMapping("/cart/clear/")
//    @CrossOrigin(origins="*")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        System.out.println("Clear Cart");
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @GetMapping("/cart")
//    @CrossOrigin(origins="*")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }




}
