package com.example.spring.dto;

import lombok.Getter;
import lombok.Setter;

public class UserDto {
  private int id;
  private String username;

  @Getter @Setter
  public static class RegisterRequest {
    private String username;
    private String password;
  }

  @Getter @Setter
  public static class LoginRequest {
    private String username;
    private String password;
  }
}
