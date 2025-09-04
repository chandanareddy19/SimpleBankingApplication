package com.kusulu.UserMicroService.Controller;

import com.kusulu.UserMicroService.Model.Users;
import com.kusulu.UserMicroService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    UserService service;
    @PostMapping("/createUser")
    public ResponseEntity<Users> createUser(@RequestBody Users user){
        return service.createUser(user);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<Users> getByUserId(@PathVariable int id){
        return service.getByUserId(id);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateById(@RequestBody Users user,@PathVariable int id){
        return service.updateById(user,id);
    }
    @GetMapping("deactivate/{id}")
    public ResponseEntity<String> deactivateAccount(@PathVariable Long id){
        return service.deactivateAccount(id);
    }
    @GetMapping("activate/{id}")
    public ResponseEntity<String> activatedAccount(@PathVariable Long id){
        return service.activatedAccount(id);
    }
}
