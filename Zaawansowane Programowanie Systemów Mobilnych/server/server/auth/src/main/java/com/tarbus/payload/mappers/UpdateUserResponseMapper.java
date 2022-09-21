package com.tarbus.payload.mappers;

import com.tarbus.payload.entity.User;
import com.tarbus.payload.response.UpdateUserResponse;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserResponseMapper {
  public UpdateUserResponse mapToEntity(User user) {
    UpdateUserResponse updateUserResponse = new UpdateUserResponse();
    updateUserResponse.setId(user.getId());
    updateUserResponse.setEmail(user.getEmail());
    updateUserResponse.setFirstName(user.getFirstName());
    updateUserResponse.setLastName(user.getLastName());
    updateUserResponse.setPhone(user.getPhone());
    updateUserResponse.setRoles(user.getRoles());
    updateUserResponse.setCompanies(user.getCompanies());
    return updateUserResponse;
  }
}
