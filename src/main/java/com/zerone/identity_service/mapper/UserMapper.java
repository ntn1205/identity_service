package com.zerone.identity_service.mapper;

import com.zerone.identity_service.dto.request.CreateUserRequest;
import com.zerone.identity_service.dto.request.UpdateUserRequest;
import com.zerone.identity_service.dto.response.UserResponse;
import com.zerone.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UpdateUserRequest request);
}
