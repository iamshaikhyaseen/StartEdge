package com.yaseen.StartEdge.controller;

import com.yaseen.StartEdge.model.User;
import com.yaseen.StartEdge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users= userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User createdUser=userService.createUser(user);
        return  new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        User user1=userService.getUserFromEmail(user.getEmail());
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

}
