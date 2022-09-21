package com.tarbus.services.impl;

import com.tarbus.exceptions.BadRequestException;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.mappers.UserMapper;
import com.tarbus.payload.request.CreateUserRequest;
import com.tarbus.payload.request.UpdateUserRequest;
import com.tarbus.repositories.jpa.RoleRepository;
import com.tarbus.repositories.jpa.UserRepository;
import com.tarbus.services.UserDataService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDataServiceImpl implements UserDataService {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder encoder;

  public UserDataServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository, PasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.roleRepository = roleRepository;
    this.encoder = encoder;
  }

  @Override
  public User getUserById(String userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
      return userOptional.get();
    } else {
      throw new BadRequestException("Error: User not found");
    }
  }

  @Override
  public User createUser(CreateUserRequest createUserRequest) {
    User user = userMapper.mapToEntity(createUserRequest);
    return userRepository.save(user);
  }

  @Override
  public User updateUser(UpdateUserRequest updateUserRequest) {
    User user = userMapper.mapToEntity(updateUserRequest);
    return userRepository.save(user);
  }

  @Override
  public List<Role> getUserRoles(String userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
      return roleRepository.findUserRoles(userOptional.get().getId());
    } else {
      throw new BadRequestException("Error: User not found");
    }
  }

  @Override
  public void removeUser(String userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
      userRepository.delete(userOptional.get());
    } else {
      throw new BadRequestException("Error: User not found");
    }
  }

  @Override
  public void updatePassword(String userId, String passwordHash) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      if (passwordHash != null) {
        user.setPassword(encoder.encode(passwordHash));
      } else {
        user.setPassword(null);
      }
      userRepository.save(user);
    } else {
      throw new BadRequestException("Error: User not found");
    }
  }

  @Override
  public Set<User> getUsersByRole(Role role) {
    return new HashSet<>(userRepository.getUsersByRolesContains(role));
  }

  @Override
  public List<User> getAllUsers(String searchPattern) {
    return userRepository.findByName(searchPattern);
  }
}
