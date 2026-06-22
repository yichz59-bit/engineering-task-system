package com.example.engineeringtasksystem.task.dto;

import java.time.LocalDate;

public record TaskResponse(
    Long id,
    String taskCode,
    String taskName,
    String projectName,
    String assignee,
    String status,
    String priority,
    LocalDate deadline) {
}
