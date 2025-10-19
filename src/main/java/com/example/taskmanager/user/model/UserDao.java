package com.example.taskmanager.user.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
//    User findByEmail(String email);
//    Boolean existsByEmail(String email);
    Boolean existsByUserName(String username);
}
