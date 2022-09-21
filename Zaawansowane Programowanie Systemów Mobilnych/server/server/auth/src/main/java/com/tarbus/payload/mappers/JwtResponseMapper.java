package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.AccessTokenDto;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtResponseMapper {
  private final FileObjectDtoMapper fileObjectDtoMapper;

  @Autowired
  public JwtResponseMapper(FileObjectDtoMapper fileObjectDtoMapper) {
    this.fileObjectDtoMapper = fileObjectDtoMapper;
  }

  public JwtResponse mapToResponse(User user, String jwtToken) {
    AccessTokenDto accessTokenDto = new AccessTokenDto("Bearer", jwtToken);
    JwtResponse jwtResponse = new JwtResponse();
    jwtResponse.setToken(accessTokenDto);
    jwtResponse.setId(user.getId());
    jwtResponse.setEmail(user.getEmail());
    jwtResponse.setFirstName(user.getFirstName());
    jwtResponse.setLastName(user.getLastName());
    jwtResponse.setRoles(user.getRoles());
    jwtResponse.setAvatar(fileObjectDtoMapper.mapToEntity(user.getAvatar()));

    return jwtResponse;
  }
}
