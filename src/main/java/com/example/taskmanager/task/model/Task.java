package com.example.taskmanager.task.model;


import com.example.taskmanager.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private PriorityType priorityType;

    private TaskStatus taskStatus = TaskStatus.ToDo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by",  nullable = false)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_by")
    private User updatedBy;

    private LocalDateTime createdAt =  LocalDateTime.now();

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delete_by")
    private User deletedBy;

    private LocalDateTime deletedAt;

    private boolean deleted = false;
}
