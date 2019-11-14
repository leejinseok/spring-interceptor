package com.example.spring.controller;

import com.example.spring.common.ErrorResponse;
import com.example.spring.exception.NoJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advide {

  @ExceptionHandler({BadCredentialsException.class, ExpiredJwtException.class, NoJwtException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponse custom(Exception exception) {
    return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
  }
}
