package com.tarbus.payload.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccessTokenDto {
  private String token;
  private String type;

  public AccessTokenDto(String token, String type) {
    this.token = token;
    this.type = type;
  }
}
