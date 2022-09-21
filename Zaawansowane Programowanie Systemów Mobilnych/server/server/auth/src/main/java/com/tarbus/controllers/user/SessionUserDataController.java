package com.tarbus.controllers.user;

import com.tarbus.exceptions.RequestException;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.mappers.UserResponseMapper;
import com.tarbus.payload.request.UpdateUserPasswordRequest;
import com.tarbus.payload.request.UpdateUserRequest;
import com.tarbus.payload.response.UserResponse;
import com.tarbus.services.AuthService;
import com.tarbus.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class SessionUserDataController {
  private final AuthService authService;
  private final UserDataService userDataService;
  private final UserResponseMapper userResponseMapper;

  @Autowired
  public SessionUserDataController(UserResponseMapper userResponseMapper, AuthService authService, UserDataService userDataService) {
    this.userResponseMapper = userResponseMapper;
    this.authService = authService;
    this.userDataService = userDataService;
  }


  @PostMapping("/user")
  public ResponseEntity<?> updateSessionUser(@RequestBody UpdateUserRequest updateUserRequest) {
    try {
      User currentUser = authService.getSessionUser();
      updateUserRequest.setId(currentUser.getId());
      User user = userDataService.updateUser(updateUserRequest);
      UserResponse userResponse = userResponseMapper.mapToEntity(user);
      return new ResponseEntity<>(userResponse, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }


  @GetMapping("/user/roles")
  public ResponseEntity<?> getSessionUserRoles() {
    try {
      User currentUser = authService.getSessionUser();
      List<Role> roles = userDataService.getUserRoles(currentUser.getId());
      return new ResponseEntity<>(roles, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @PostMapping("/user/password")
  public ResponseEntity<?> updateSessionUserPassword(@RequestBody UpdateUserPasswordRequest updateUserPasswordRequest) {
    try {
      User currentUser = authService.getSessionUser();
      if (updateUserPasswordRequest.isClearPassword()) {
        userDataService.updatePassword(currentUser.getId(), null);
      }
      if (updateUserPasswordRequest.getPassword() != null) {
        userDataService.updatePassword(currentUser.getId(), updateUserPasswordRequest.getPassword());
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
