package com.tarbus.services.impl;

import com.tarbus.exceptions.BadRequestException;
import com.tarbus.exceptions.UnauthorizedException;
import com.tarbus.payload.mappers.JwtResponseMapper;
import com.tarbus.payload.mappers.SignupResponseMapper;
import com.tarbus.payload.enums.ERole;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.request.LoginRequest;
import com.tarbus.payload.request.SignupRequest;
import com.tarbus.payload.response.JwtResponse;
import com.tarbus.payload.response.SignupResponse;
import com.tarbus.repositories.jpa.RoleRepository;
import com.tarbus.repositories.jpa.UserRepository;
import com.tarbus.utilities.JwtUtils;
import com.tarbus.security.UserDetailsImpl;
import com.tarbus.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final PasswordEncoder encoder;
  private final SignupResponseMapper signupResponseMapper;
  private final JwtResponseMapper jwtResponseMapper;

  @Autowired
  public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, SignupResponseMapper signupResponseMapper,JwtResponseMapper jwtResponseMapper) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.encoder = encoder;
    this.jwtResponseMapper = jwtResponseMapper;
    this.signupResponseMapper = signupResponseMapper;
  }

  @Override
  public JwtResponse authenticateUser(LoginRequest loginRequest) throws UnauthorizedException {

    UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
    Authentication authentication = authenticationManager.authenticate(userPasswordAuthenticationToken);
    System.out.println(loginRequest);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwtToken = jwtUtils.generateJwtToken(authentication);
    User user = getSessionUser();
    return jwtResponseMapper.mapToResponse(user, jwtToken);
  }

  @Override
  public SignupResponse registerUser(SignupRequest signUpRequest) throws BadRequestException {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      throw new BadRequestException("Error: Email is already in use!");
    }

    User user = new User(signUpRequest.getEmail(),
      encoder.encode(signUpRequest.getPassword()));

    Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
    if( !userRole.isPresent() ) {
      throw new BadRequestException("Error: Server error. Cannot found default role");
    }
    user.setRoles(new HashSet<>(Collections.singletonList(userRole.get())));
    user.setFirstName(signUpRequest.getFirstName());
    user.setLastName(signUpRequest.getLastName());
    user.setPhone(signUpRequest.getPhone());
    User createdUser = userRepository.save(user);
    return signupResponseMapper.mapToResponse(createdUser);
  }

  public User getSessionUser() throws UnauthorizedException {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    try {
      return getUserFromPrincipal((UserDetailsImpl) authentication.getPrincipal());
    } catch (Exception e) {
      throw new UnauthorizedException();
    }

  }

  @Override
  public User getUserFromPrincipal(UserDetailsImpl principal) throws BadRequestException {
    String email = principal.getEmail();
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if( optionalUser.isPresent() ) {
      return optionalUser.get();
    } else {
      throw new BadRequestException("Error: User not found");
    }
  }
}
