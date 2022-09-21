package com.tarbus.payload.dto;

import com.tarbus.payload.enums.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {
  private Long id;
  private ERole name;
  private String objectId;
}
