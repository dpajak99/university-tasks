package com.tarbus.payload.request;

import com.tarbus.payload.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequest {
  private String id;
  private String email;
  private String firstName;
  private String lastName;
  private String phone;
  private String avatarId;
  private Set<Role> roles;
  private Set<String> companiesId;
}
