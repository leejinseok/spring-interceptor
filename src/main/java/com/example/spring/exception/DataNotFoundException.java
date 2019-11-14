package com.example.spring.exception;

import java.util.function.Supplier;

public class DataNotFoundException extends RuntimeException {
  public DataNotFoundException(String msg) {
    super(msg);
  }
}
