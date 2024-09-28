package com.zerone.identity_service.service;

import com.zerone.identity_service.dto.CreateUserRequest;
import com.zerone.identity_service.dto.UpdateUserRequest;
import com.zerone.identity_service.entity.User;
import com.zerone.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User userCreate(CreateUserRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setAddress(request.getAddress());

        return userRepository.save(user);
    }

    public User userUpdate(String userId, UpdateUserRequest request) {
        User user = userGetById(userId);

        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setAddress(request.getAddress());

        return userRepository.save(user);
    }

    public List<User> userGetAll() {
        return userRepository.findAll();
    }

    public User userGetById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public void userDelete(String userId) {
        userRepository.deleteById(userId);
    }
}
