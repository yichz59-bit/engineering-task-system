package com.example.engineeringtasksystem.task.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.engineeringtasksystem.common.ResourceNotFoundException;
import com.example.engineeringtasksystem.task.dto.TaskCreateRequest;
import com.example.engineeringtasksystem.task.dto.TaskResponse;
import com.example.engineeringtasksystem.task.dto.TaskUpdateRequest;
import com.example.engineeringtasksystem.task.model.Task;
import com.example.engineeringtasksystem.task.model.TaskStatus;

@Service
public class TaskService {

  private static final Set<String> SUPPORTED_PRIORITIES = Set.of("高", "中", "低");

  private final Map<Long, Task> tasks = new LinkedHashMap<>();
  private final AtomicLong idGenerator = new AtomicLong(0);

  public TaskService() {
    initializeMockTasks();
  }

  public List<TaskResponse> listTasks() {
    return tasks.values().stream()
        .map(this::toResponse)
        .toList();
  }

  public TaskResponse getTask(Long id) {
    return toResponse(findTask(id));
  }

  public TaskResponse createTask(TaskCreateRequest request) {
    Long id = idGenerator.incrementAndGet();
    Task task = new Task(
        id,
        createTaskCode(id),
        request.taskName(),
        request.projectName(),
        request.assignee(),
        TaskStatus.fromValue(request.status()),
        normalizePriority(request.priority()),
        request.deadline());

    tasks.put(id, task);
    return toResponse(task);
  }

  public TaskResponse updateTask(Long id, TaskUpdateRequest request) {
    Task task = findTask(id);

    // Keep id and taskCode stable; editing only changes the business fields users can maintain.
    task.update(
        request.taskName(),
        request.projectName(),
        request.assignee(),
        TaskStatus.fromValue(request.status()),
        normalizePriority(request.priority()),
        request.deadline());

    return toResponse(task);
  }

  public void deleteTask(Long id) {
    if (tasks.remove(id) == null) {
      throw new ResourceNotFoundException("Task not found: " + id);
    }
  }

  private void initializeMockTasks() {
    List<Task> initialTasks = List.of(
        newTask("需求评审会议准备", "工程任务系统", "张晨", TaskStatus.IN_PROGRESS, "高", LocalDate.of(2026, 6, 24)),
        newTask("任务列表页面原型确认", "工程任务系统", "李然", TaskStatus.COMPLETED, "中", LocalDate.of(2026, 6, 20)),
        newTask("项目成员权限梳理", "协作平台升级", "王宁", TaskStatus.NOT_STARTED, "中", LocalDate.of(2026, 6, 28)));

    initialTasks.forEach(task -> tasks.put(task.getId(), task));
  }

  private Task newTask(
      String taskName,
      String projectName,
      String assignee,
      TaskStatus status,
      String priority,
      LocalDate deadline) {
    Long id = idGenerator.incrementAndGet();

    return new Task(id, createTaskCode(id), taskName, projectName, assignee, status, priority, deadline);
  }

  private Task findTask(Long id) {
    Task task = tasks.get(id);

    if (task == null) {
      throw new ResourceNotFoundException("Task not found: " + id);
    }

    return task;
  }

  private String createTaskCode(Long id) {
    return "TASK-%03d".formatted(id);
  }

  private String normalizePriority(String priority) {
    if (!SUPPORTED_PRIORITIES.contains(priority)) {
      throw new IllegalArgumentException("Unsupported task priority: " + priority);
    }

    return priority;
  }

  private TaskResponse toResponse(Task task) {
    return new TaskResponse(
        task.getId(),
        task.getTaskCode(),
        task.getTaskName(),
        task.getProjectName(),
        task.getAssignee(),
        task.getStatus().getDisplayName(),
        task.getPriority(),
        task.getDeadline());
  }
}
