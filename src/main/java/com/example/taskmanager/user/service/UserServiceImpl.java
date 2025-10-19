package com.example.taskmanager.user.service;

import com.example.taskmanager.security.JwtUtil;
import com.example.taskmanager.user.dto.LoginUserRequest;
import com.example.taskmanager.user.dto.RegisterUserRequest;
import com.example.taskmanager.user.dto.UserLoginResponse;
import com.example.taskmanager.user.dto.UserResponse;
import com.example.taskmanager.user.exception.UserAlreadyExistsException;
import com.example.taskmanager.user.exception.UserNotFoundException;
import com.example.taskmanager.user.mapper.UserMapper;
import com.example.taskmanager.user.model.User;
import com.example.taskmanager.user.model.UserDao;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserDao userDao, UserMapper userMapper, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userDao = userDao;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public UserResponse register(RegisterUserRequest request) {
//        if (userDao.existsByEmail(request.getEmail())) {
//            throw new UserAlreadyExistsException("email", request.getEmail());
//        }
        if (userDao.existsByUserName(request.getUserName())) {
            throw new UserAlreadyExistsException("username", request.getUserName());
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User newUser = userMapper.toEntityRegister(request);
        return userMapper.toResponse(userDao.save(newUser));
    }

    @Override
    @Transactional
    public UserLoginResponse login(LoginUserRequest loginUserRequest) {
        if (!userDao.existsByUserName(loginUserRequest.getUserName())) {
            throw new UserNotFoundException("username", loginUserRequest.getUserName());
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUserRequest.getUserName(), loginUserRequest.getPassword())
        );
        String token = jwtUtil.generateToken(authentication.getName());
        return UserLoginResponse.builder()
                .token(token)
                .userName(loginUserRequest.getUserName())
                .build();
//        return jwtUtil.generateToken(authentication.getName());
    }
}
