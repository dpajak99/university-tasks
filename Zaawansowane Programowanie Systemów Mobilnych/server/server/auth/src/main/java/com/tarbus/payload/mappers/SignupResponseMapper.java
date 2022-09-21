package com.tarbus.payload.mappers;

import com.tarbus.payload.entity.User;
import com.tarbus.payload.response.SignupResponse;
import org.springframework.stereotype.Component;

@Component
public class SignupResponseMapper {
  public SignupResponse mapToResponse(User user) {
    SignupResponse signupResponse = new SignupResponse();
    signupResponse.setId(user.getId());
    return signupResponse;
  }
}
