package com.example.engineeringtasksystem.task.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.engineeringtasksystem.common.ApiResponse;
import com.example.engineeringtasksystem.task.dto.TaskCreateRequest;
import com.example.engineeringtasksystem.task.dto.TaskResponse;
import com.example.engineeringtasksystem.task.dto.TaskUpdateRequest;
import com.example.engineeringtasksystem.task.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping
  public ApiResponse<List<TaskResponse>> listTasks() {
    return ApiResponse.success(taskService.listTasks());
  }

  @GetMapping("/{id}")
  public ApiResponse<TaskResponse> getTask(@PathVariable Long id) {
    return ApiResponse.success(taskService.getTask(id));
  }

  @PostMapping
  public ApiResponse<TaskResponse> createTask(@Valid @RequestBody TaskCreateRequest request) {
    return ApiResponse.success(taskService.createTask(request));
  }

  @PutMapping("/{id}")
  public ApiResponse<TaskResponse> updateTask(
      @PathVariable Long id,
      @Valid @RequestBody TaskUpdateRequest request) {
    return ApiResponse.success(taskService.updateTask(id, request));
  }

  @DeleteMapping("/{id}")
  public ApiResponse<Void> deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
    return ApiResponse.success();
  }
}
