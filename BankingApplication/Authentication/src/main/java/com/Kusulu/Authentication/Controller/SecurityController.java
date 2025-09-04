package com.Kusulu.Authentication.Controller;

import com.Kusulu.Authentication.Model.User;
import com.Kusulu.Authentication.Service.JwtService;
import com.Kusulu.Authentication.Service.LoginService;
import com.Kusulu.Authentication.Service.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityController {
    @GetMapping("/")
    public String hello(HttpServletRequest http){
        return "how are you"+http.getSession().getId();
    }
    @Autowired
    LoginService service;
    @Autowired
    JwtService jwtService;
    @PostMapping("/register")
    public User details(@RequestBody User user){
        return service.details(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
       return service.verify(user);
    }
    @GetMapping("/validate")

    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token){
        if (jwtService.validate(token)) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
