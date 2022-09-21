package com.tarbus.exceptions;

import com.tarbus.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UnauthorizedException extends RequestException {
  public UnauthorizedException() {}
  public UnauthorizedException(String s) {
    super(s);
  }

  @Override
  public ResponseEntity<?> toResponseEntity() {
    if(  getMessage() == null || getMessage().isEmpty() ) {
      MessageResponse messageResponse = new MessageResponse("Error: Unauthorized");
      return new ResponseEntity<>(messageResponse, HttpStatus.UNAUTHORIZED);
    }
    MessageResponse messageResponse = new MessageResponse(getMessage());
    return new ResponseEntity<>(messageResponse, HttpStatus.UNAUTHORIZED);
  }
}
