package com.example.taskmanager.user.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUserName(String username);
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUserName(String username);
}
