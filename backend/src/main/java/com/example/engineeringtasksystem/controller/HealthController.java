package com.example.engineeringtasksystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

  @GetMapping("/health")
  public HealthResponse health() {
    return new HealthResponse("ok", "Engineering Task System backend is running");
  }

  // This endpoint verifies the application and Web layer only; it does not check database connectivity.
  public record HealthResponse(String status, String message) {
  }
}
