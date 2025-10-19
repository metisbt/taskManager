package com.example.taskmanager.user.mapper;

import com.example.taskmanager.user.dto.LoginUserRequest;
import com.example.taskmanager.user.dto.RegisterUserRequest;
import com.example.taskmanager.user.dto.UpdateUserRequest;
import com.example.taskmanager.user.dto.UserResponse;
import com.example.taskmanager.user.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    User toEntityRegister(RegisterUserRequest registerUserRequest);

//    User toEntityLogin(LoginUserRequest loginUserRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UpdateUserRequest updateUserRequest, @MappingTarget User user);
}
