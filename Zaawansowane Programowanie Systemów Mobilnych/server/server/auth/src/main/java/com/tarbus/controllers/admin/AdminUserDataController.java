
package com.tarbus.controllers.admin;

import com.tarbus.exceptions.RequestException;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.mappers.UpdateUserResponseMapper;
import com.tarbus.payload.mappers.UserResponseMapper;
import com.tarbus.payload.request.CreateUserRequest;
import com.tarbus.payload.request.UpdateUserPasswordRequest;
import com.tarbus.payload.request.UpdateUserRequest;
import com.tarbus.payload.response.UpdateUserResponse;
import com.tarbus.payload.response.UserResponse;
import com.tarbus.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
public class AdminUserDataController {
  final UserDataService userDataService;
  final UserResponseMapper userResponseMapper;
  final UpdateUserResponseMapper updateUserResponseMapper;

  @Autowired
  public AdminUserDataController(UserDataService userDataService, UpdateUserResponseMapper updateUserResponseMapper, UserResponseMapper userResponseMapper) {
    this.userDataService = userDataService;
    this.updateUserResponseMapper = updateUserResponseMapper;
    this.userResponseMapper = userResponseMapper;
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<?> getUserById(@PathVariable String id) {
    try {
      User user = userDataService.getUserById(id);
      UserResponse userResponse = userResponseMapper.mapToEntity(user);
      return new ResponseEntity<>(userResponse, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @GetMapping("/users")
  public ResponseEntity<?> getAllUsers(@RequestParam String searchPattern) {
    try {
      List<User> users = userDataService.getAllUsers(searchPattern);
      List<UserResponse> userResponse = users.stream().map(userResponseMapper::mapToEntity).collect(Collectors.toList());
      return new ResponseEntity<>(userResponse, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @PutMapping("/user")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> createUser(@RequestBody CreateUserRequest createUserRequest) {
    User user = userDataService.createUser(createUserRequest);
    UpdateUserResponse updateUserResponse = updateUserResponseMapper.mapToEntity(user);
    return new ResponseEntity<>(updateUserResponse, HttpStatus.OK);
  }

  @PostMapping("/user/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
    try {
      User user = userDataService.updateUser(updateUserRequest);
      UpdateUserResponse updateUserResponse = updateUserResponseMapper.mapToEntity(user);
      return new ResponseEntity<>(updateUserResponse, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @DeleteMapping("/user/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> removeUser(@PathVariable String id) {
    try {
      userDataService.removeUser(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @GetMapping("/user/{id}/roles")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> getUserRoles(@PathVariable String id) {
    try {
      List<Role> roles = userDataService.getUserRoles(id);
      return new ResponseEntity<>(roles, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @PostMapping("/user/{id}/password")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> updateUserPassword(@PathVariable String id, @RequestBody UpdateUserPasswordRequest updateUserPasswordRequest) {
    try {
      if (updateUserPasswordRequest.isClearPassword()) {
        userDataService.updatePassword(id, null);
      }
      if (updateUserPasswordRequest.getPassword() != null) {
        userDataService.updatePassword(id, updateUserPasswordRequest.getPassword());
      }
      if (updateUserPasswordRequest.isSendEmail()) {
        // TODO(dominik): Finish it
      }
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }
}
