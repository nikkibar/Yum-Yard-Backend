package com.nitesh.Controller;

import com.nitesh.Service.CustomerUserDetailsService;
import com.nitesh.config.JwtProvider;
import com.nitesh.model.Cart;
import com.nitesh.model.USER_ROLE;
import com.nitesh.model.User;
import com.nitesh.repository.CartRepository;
import com.nitesh.repository.UserRepository;
import com.nitesh.request.LoginRequest;
import com.nitesh.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepository cartRepository;


  @PostMapping("/signup")
//  @CrossOrigin(origins="*")
  public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

      User isEmailExists = userRepository.findByEmail(user.getEmail());

      if(isEmailExists != null){
          throw new Exception("Email is already used with another account");
      }

      User createdUser = new User();
      createdUser.setEmail(user.getEmail());
      createdUser.setFullName(user.getFullName());
      createdUser.setRole(user.getRole());
      createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

      User savedUser = userRepository.save(createdUser);

      Cart cart = new Cart();
      cart.setCustomer(savedUser);
      cartRepository.save(cart);

      Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
      SecurityContextHolder.getContext().setAuthentication(authentication);

      String jwt = jwtProvider.generateToken(authentication);

      AuthResponse authResponse = new AuthResponse();
      authResponse.setJwt(jwt);
      authResponse.setMessage("Registration Successful");
      authResponse.setRole(savedUser.getRole());

      return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
  }



  @PostMapping("/signin")
//  @CrossOrigin(origins="*")
  public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req){

      String email = req.getEmail();
      String password = req.getPassword();

      Authentication authentication = authenticate(email,password);

      Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
      String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

      String jwt = jwtProvider.generateToken(authentication);

      AuthResponse authResponse = new AuthResponse();
      authResponse.setJwt(jwt);
      authResponse.setMessage("Login Successful");
      authResponse.setRole(USER_ROLE.valueOf(role));

      return new ResponseEntity<>(authResponse, HttpStatus.OK);

  }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid username......");
        }

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password......");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }


}
