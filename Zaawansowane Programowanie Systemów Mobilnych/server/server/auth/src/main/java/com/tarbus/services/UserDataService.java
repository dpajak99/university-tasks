package com.tarbus.services;

import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.enums.ERole;
import com.tarbus.payload.request.CreateUserRequest;
import com.tarbus.payload.request.UpdateUserRequest;

import java.util.List;
import java.util.Set;

public interface UserDataService {
  User getUserById(String userId);
  User createUser(CreateUserRequest createUserRequest);
  User updateUser(UpdateUserRequest updateUserRequest);
  List<Role> getUserRoles(String userId);
  void removeUser(String userId);
  void updatePassword(String userId, String passwordHash);
  Set<User> getUsersByRole(Role role);
  List<User> getAllUsers(String searchPattern);
}
