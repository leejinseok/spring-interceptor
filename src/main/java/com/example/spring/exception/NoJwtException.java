package com.example.spring.exception;

import org.springframework.security.core.AuthenticationException;

public class NoJwtException extends AuthenticationException {
  public NoJwtException(String msg) {
    super(msg);
  }
}
