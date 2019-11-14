package com.example.spring.controller;

import com.example.spring.domain.User;
import com.example.spring.dto.UserDto;
import com.example.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.UserSessionEvent;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

  private final UserService userService;

  @Autowired
  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<Integer> register(@RequestBody UserDto.RegisterRequest dto) {
    int id = userService.register(dto.getUsername(), dto.getPassword()).getId();
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserDto.LoginRequest dto) {
    String token = userService.login(dto.getUsername(), dto.getPassword());
    return new ResponseEntity<>(token, HttpStatus.OK);
  }
}
