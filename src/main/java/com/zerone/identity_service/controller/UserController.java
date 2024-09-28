package com.zerone.identity_service.controller;


import com.zerone.identity_service.dto.CreateUserRequest;
import com.zerone.identity_service.dto.UpdateUserRequest;
import com.zerone.identity_service.entity.User;
import com.zerone.identity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody CreateUserRequest request) {
        return userService.userCreate(request);
    }

    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UpdateUserRequest request) {
        return userService.userUpdate(userId, request);
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.userGetAll();
    }

    @GetMapping("/{userId}")
    User getUserById(@PathVariable String userId) {
        return userService.userGetById(userId);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.userDelete(userId);
        return "User has been deleted!";
    }
}
