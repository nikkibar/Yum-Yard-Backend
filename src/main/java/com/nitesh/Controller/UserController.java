package com.nitesh.Controller;

import com.nitesh.Service.UserService;
import com.nitesh.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
//    @CrossOrigin(origins="*")
    private ResponseEntity<User>  findUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return new  ResponseEntity<>(user, HttpStatus.OK);
    }
}
