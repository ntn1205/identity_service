package com.zerone.identity_service.controller;


import com.zerone.identity_service.dto.request.ApiResponse;
import com.zerone.identity_service.dto.request.CreateUserRequest;
import com.zerone.identity_service.dto.request.UpdateUserRequest;
import com.zerone.identity_service.dto.response.UserResponse;
import com.zerone.identity_service.entity.User;
import com.zerone.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid CreateUserRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Create user successfully.");
        apiResponse.setResult(userService.userCreate(request));
        return apiResponse;
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId, @RequestBody UpdateUserRequest request) {
        return userService.userUpdate(userId, request);
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.userGetAll();
    }

    @GetMapping("/{userId}")
    UserResponse getUserById(@PathVariable String userId) {

        return userService.userGetById(userId);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId) {
        userService.userDelete(userId);
        return "User has been deleted!";
    }
}
