package com.tarbus.utilities;

import com.tarbus.exceptions.BadRequestException;
import com.tarbus.exceptions.UnauthorizedException;
import com.tarbus.repositories.jpa.RoleRepository;
import com.tarbus.services.AuthService;

import com.tarbus.payload.enums.ERole;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleChecker {
  private final AuthService authService;
  private final RoleRepository roleRepository;

  @Autowired
  public RoleChecker(AuthService authService, RoleRepository roleRepository) {
    this.authService = authService;
    this.roleRepository = roleRepository;
  }

  public boolean hasCurrentUserRole(ERole roleName, String objectId) throws UnauthorizedException {
    User user = authService.getSessionUser();
    Optional<Role> roleOptional = roleRepository.findByNameAndObjectId(roleName, objectId);
    if( roleOptional.isPresent() ) {
      return user.getRoles().contains(roleOptional.get());
    }
    throw new BadRequestException("Error: Role not found");
  }

  public boolean hasCurrentUserAnyRoles(List<ERole> roleNames, String objectId) throws UnauthorizedException {
    User user = authService.getSessionUser();
    Set<Role> userRoles = user.getRoles();
    for( ERole roleName : roleNames ) {
      Optional<Role> roleOptional = roleRepository.findByNameAndObjectId(roleName, objectId);
      if( roleOptional.isPresent() ) {
        if (userRoles.contains(roleOptional.get())) {
          return true;
        }
      } else {
        throw new BadRequestException("Error: Role not found");
      }
    }
    return false;
  }

  public boolean hasCurrentUserAnyAuthorities(List<ERole> roleNames) throws UnauthorizedException {
    User user = authService.getSessionUser();
    Set<Role> userRoles = user.getRoles();
    for( ERole roleName : roleNames ) {
      Optional<Role> roleOptional = roleRepository.findByName(roleName);
      if( roleOptional.isPresent() ) {
        if (userRoles.contains(roleOptional.get())) {
          return true;
        }
      } else {
        throw new BadRequestException("Error: Role not found");
      }
    }
    return false;
  }


  public boolean isAdmin() throws UnauthorizedException {
    User user = authService.getSessionUser();
    for( Role role : user.getRoles() ) {
      if( role.getName() == ERole.ROLE_ADMIN ) {
        return true;
      }
    }
    return false;
  }


}
