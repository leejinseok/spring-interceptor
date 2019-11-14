package com.example.spring.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorResponse {

  private int status;
  private String message;
  private LocalDateTime timestamp;

  public ErrorResponse(final int status, final String message) {
    this.status = status;
    this.message = message;
    this.timestamp = LocalDateTime.now();
  }
}
