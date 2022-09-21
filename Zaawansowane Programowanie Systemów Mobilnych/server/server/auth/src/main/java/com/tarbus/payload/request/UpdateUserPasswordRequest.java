package com.tarbus.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserPasswordRequest {
  @Nullable
  private String password;
  private boolean sendEmail = false;
  private boolean clearPassword = false;
}
