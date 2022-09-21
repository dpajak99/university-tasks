package com.tarbus.payload.mappers;

import com.tarbus.payload.entity.User;
import com.tarbus.payload.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {
  private final FileObjectDtoMapper fileObjectDtoMapper;

  @Autowired
  public UserResponseMapper(FileObjectDtoMapper fileObjectDtoMapper) {
    this.fileObjectDtoMapper = fileObjectDtoMapper;
  }


  public UserResponse mapToEntity(User user) {
    UserResponse userResponse = new UserResponse();
    userResponse.setId(user.getId());
    userResponse.setEmail(user.getEmail());
    userResponse.setFirstName(user.getFirstName());
    userResponse.setLastName(user.getLastName());
    userResponse.setPhone(user.getPhone());
    userResponse.setCompanies(user.getCompanies());
    userResponse.setAvatar(fileObjectDtoMapper.mapToEntity(user.getAvatar()));
    return userResponse;
  }
}
