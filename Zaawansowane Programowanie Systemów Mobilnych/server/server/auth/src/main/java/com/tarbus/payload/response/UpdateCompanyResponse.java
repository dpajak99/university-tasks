package com.tarbus.payload.response;

import com.tarbus.payload.dto.FileObjectDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateCompanyResponse {
  private String id;
  private String name;
  private String website;
  private String phone;
  private String email;
  private String timezone;
  private String lang;
  private FileObjectDto avatar;
}
