package com.example.engineeringtasksystem.common;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Void>> handleValidationException(
      MethodArgumentNotValidException exception) {
    String message = exception.getBindingResult().getFieldErrors().stream()
        .map(this::formatFieldError)
        .collect(Collectors.joining("; "));

    return ResponseEntity.badRequest().body(ApiResponse.fail(400, message));
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(
      ResourceNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail(404, exception.getMessage()));
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException exception) {
    return ResponseEntity.badRequest().body(ApiResponse.fail(400, exception.getMessage()));
  }

  private String formatFieldError(FieldError error) {
    return error.getField() + " " + error.getDefaultMessage();
  }
}
