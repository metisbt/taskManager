package com.example.taskmanager.task.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDao extends JpaRepository<Task, Long> {
    Task findByTitle(String title);
}
