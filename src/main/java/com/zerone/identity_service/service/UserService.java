package com.zerone.identity_service.service;

import com.zerone.identity_service.dto.request.CreateUserRequest;
import com.zerone.identity_service.dto.request.UpdateUserRequest;
import com.zerone.identity_service.dto.response.UserResponse;
import com.zerone.identity_service.entity.User;
import com.zerone.identity_service.exception.AppException;
import com.zerone.identity_service.exception.ErrorCode;
import com.zerone.identity_service.mapper.UserMapper;
import com.zerone.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User userCreate(CreateUserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        }

        User user = userMapper.toUser(request);

        // Password encryption
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public UserResponse userUpdate(String userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<User> userGetAll() {
        return userRepository.findAll();
    }

    public UserResponse userGetById(String userId) {
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!")));
    }

    public void userDelete(String userId) {
        userRepository.deleteById(userId);
    }
}
