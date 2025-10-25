package com.example.taskmanager.task.mapper;

import com.example.taskmanager.task.dto.CreateTaskRequest;
import com.example.taskmanager.task.dto.TaskResponse;
import com.example.taskmanager.task.dto.UpdateTaskRequest;
import com.example.taskmanager.task.model.Task;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "createdBy", source = "createdBy.userName")
    @Mapping(target = "updatedBy", source = "updatedBy.userName")
    TaskResponse toResponse (Task task);

    Task toEntity (CreateTaskRequest createTaskRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task updateTaskFromDto (UpdateTaskRequest updateTaskRequest, @MappingTarget Task task);
}
