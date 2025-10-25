package com.example.taskmanager.task.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskDao extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.deleted = false")
    List<Task> findAllActiveTasks();

    @Query("SELECT t FROM Task t WHERE t.id = :id AND t.deleted = false")
    Optional<Task> findActiveById(@Param("id") Long id);
}
