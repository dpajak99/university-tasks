package com.tarbus.payload.request;

import com.tarbus.payload.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {
  private String email;
  private String firstName;
  private String lastName;
  private String phone;
  private Set<Role> roles;
  private Set<String> companiesId;
}
