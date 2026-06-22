package com.example.engineeringtasksystem.task.model;

import java.time.LocalDate;

public class Task {

  private final Long id;
  private final String taskCode;
  private String taskName;
  private String projectName;
  private String assignee;
  private TaskStatus status;
  private String priority;
  private LocalDate deadline;

  public Task(
      Long id,
      String taskCode,
      String taskName,
      String projectName,
      String assignee,
      TaskStatus status,
      String priority,
      LocalDate deadline) {
    this.id = id;
    this.taskCode = taskCode;
    this.taskName = taskName;
    this.projectName = projectName;
    this.assignee = assignee;
    this.status = status;
    this.priority = priority;
    this.deadline = deadline;
  }

  public Long getId() {
    return id;
  }

  public String getTaskCode() {
    return taskCode;
  }

  public String getTaskName() {
    return taskName;
  }

  public String getProjectName() {
    return projectName;
  }

  public String getAssignee() {
    return assignee;
  }

  public TaskStatus getStatus() {
    return status;
  }

  public String getPriority() {
    return priority;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void update(
      String taskName,
      String projectName,
      String assignee,
      TaskStatus status,
      String priority,
      LocalDate deadline) {
    this.taskName = taskName;
    this.projectName = projectName;
    this.assignee = assignee;
    this.status = status;
    this.priority = priority;
    this.deadline = deadline;
  }
}
