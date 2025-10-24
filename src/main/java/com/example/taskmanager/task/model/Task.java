package com.example.taskmanager.task.model;


import com.example.taskmanager.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

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
    @JoinColumn(name = "update_by_id", nullable = false)
    private User updateBy;

}
