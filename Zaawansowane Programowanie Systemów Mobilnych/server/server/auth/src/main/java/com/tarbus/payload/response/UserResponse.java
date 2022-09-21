package com.tarbus.payload.response;

import com.tarbus.payload.dto.FileObjectDto;
import com.tarbus.payload.entity.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
  private String id;
  private String email;
  private String firstName;
  private String lastName;
  private String phone;
  private FileObjectDto avatar;
  private Set<Company> companies;
}
