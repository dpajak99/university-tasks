package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.RoleDto;
import com.tarbus.payload.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
  public Role mapToEntity(RoleDto roleDto) {
    Role role = new Role();
    role.setId(roleDto.getId());
    role.setName(roleDto.getName());
    role.setObjectId(roleDto.getObjectId());
    return role;
  }
}
