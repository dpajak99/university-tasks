package com.tarbus.exceptions;

import com.tarbus.payload.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BadRequestException extends RequestException {
  public BadRequestException() {}
  public BadRequestException(String s) {
    super(s);
  }

  @Override
  public ResponseEntity<?> toResponseEntity() {
    if(  getMessage() == null || getMessage().isEmpty() ) {
      MessageResponse messageResponse = new MessageResponse("Error: Bad request");
      return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
    }
    MessageResponse messageResponse = new MessageResponse(getMessage());
    return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
  }
}
