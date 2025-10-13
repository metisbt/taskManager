package com.example.taskmanager.user.service;

import com.example.taskmanager.user.dto.LoginUserRequest;
import com.example.taskmanager.user.dto.RegisterUserRequest;
import com.example.taskmanager.user.dto.UserResponse;
import com.example.taskmanager.user.exception.UserAlreadyExistsException;
import com.example.taskmanager.user.exception.UserNotFoundException;
import com.example.taskmanager.user.mapper.UserMapper;
import com.example.taskmanager.user.model.Role;
import com.example.taskmanager.user.model.User;
import com.example.taskmanager.user.model.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public UserResponse register(RegisterUserRequest request) {
        if (userDao.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("email", request.getEmail());
        }
        if (userDao.existsByUserName(request.getUserName())) {
            throw new UserAlreadyExistsException("username", request.getUserName());
        }
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User newUser = userMapper.toEntityRegister(request);
        newUser.setPassword(hashedPassword);
        return userMapper.toResponse(userDao.save(newUser));
    }

    @Override
    @Transactional
    public UserResponse login(LoginUserRequest loginUserRequest) {
        if (!userDao.existsByUserName(loginUserRequest.getUserName())) {
            throw new UserNotFoundException("username", loginUserRequest.getUserName());
        }
        User loginUser = userMapper.toEntityLogin(loginUserRequest);
        return userMapper.toResponse(userDao.save(loginUser));
    }
}
