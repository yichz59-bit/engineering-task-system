package com.example.engineeringtasksystem.task.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskCreateRequest(
    @NotBlank String taskName,
    @NotBlank String projectName,
    @NotBlank String assignee,
    @NotBlank String status,
    @NotBlank String priority,
    @NotNull LocalDate deadline) {
}
