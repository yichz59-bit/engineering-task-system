package com.example.engineeringtasksystem.task.model;

import java.util.Arrays;

public enum TaskStatus {
  NOT_STARTED("未开始"),
  IN_PROGRESS("进行中"),
  COMPLETED("已完成"),
  DELAYED("已延期");

  private final String displayName;

  TaskStatus(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static TaskStatus fromValue(String value) {
    return Arrays.stream(values())
        .filter(status -> status.name().equalsIgnoreCase(value) || status.displayName.equals(value))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unsupported task status: " + value));
  }
}
