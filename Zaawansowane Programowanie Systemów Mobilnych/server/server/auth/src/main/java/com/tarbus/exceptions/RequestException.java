package com.tarbus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class RequestException extends RuntimeException {
  public RequestException() {}
  public RequestException(String s) {
    super(s);
  }

  abstract public ResponseEntity<?> toResponseEntity();
}
