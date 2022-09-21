package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.RoleDto;
import com.tarbus.payload.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoMapper {
  public RoleDto mapToEntity(Role role) {
    RoleDto roleDto = new RoleDto();
    roleDto.setId(role.getId());
    roleDto.setName(role.getName());
    roleDto.setObjectId(role.getObjectId());
    return roleDto;
  }
}
