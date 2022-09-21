package com.tarbus.payload.mappers;

import com.tarbus.exceptions.BadRequestException;
import com.tarbus.payload.entity.Company;
import com.tarbus.payload.entity.FileObject;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.request.CreateUserRequest;
import com.tarbus.payload.request.UpdateUserRequest;
import com.tarbus.repositories.jpa.CompanyRepository;
import com.tarbus.repositories.jpa.RoleRepository;
import com.tarbus.repositories.jpa.UserRepository;
import com.tarbus.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserMapper {
  final UserRepository userRepository;
  final CompanyRepository companyRepository;
  final RoleRepository roleRepository;
  final FileService fileService;

  @Autowired
  public UserMapper(UserRepository userRepository, RoleRepository roleRepository, CompanyRepository companyRepository, FileService fileService) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.companyRepository = companyRepository;
    this.fileService = fileService;
  }

  public User mapToEntity(CreateUserRequest createUserRequest) {
    User user = new User();
    user.setEmail(createUserRequest.getEmail());
    user.setFirstName(createUserRequest.getFirstName());
    user.setLastName(createUserRequest.getLastName());
    user.setPhone(createUserRequest.getPhone());

    Set<Role> newRoles = prepareRoles(createUserRequest.getRoles());
    user.setRoles(newRoles);

    Set<Company> newCompanies = prepareCompanies(createUserRequest.getCompaniesId());
    user.setCompanies(newCompanies);

    return user;
  }

  public User mapToEntity(UpdateUserRequest updateUserRequest) {
    Optional<User> userOptional = userRepository.findById(updateUserRequest.getId());
    if( !userOptional.isPresent() ) {
      throw new BadRequestException("Error: User not found");
    }
    User user = userOptional.get();
    if( updateUserRequest.getEmail() != null ) {
      user.setEmail(updateUserRequest.getEmail());
    }
    if( updateUserRequest.getFirstName() != null ) {
      user.setFirstName(updateUserRequest.getFirstName());
    }
    if( updateUserRequest.getLastName() != null ) {
      user.setLastName(updateUserRequest.getLastName());
    }
    if( updateUserRequest.getPhone() != null ) {
      user.setPhone(updateUserRequest.getPhone());
    }

    if( updateUserRequest.getRoles() != null ) {
      Set<Role> newRoles = prepareRoles(updateUserRequest.getRoles());
      user.setRoles(newRoles);
    }

    if( updateUserRequest.getAvatarId() != null ) {
      FileObject fileObject = fileService.getFileById(updateUserRequest.getAvatarId());
      user.setAvatar(fileObject);
    }

    if( updateUserRequest.getCompaniesId() != null ) {
      Set<Company> newCompanies = prepareCompanies(updateUserRequest.getCompaniesId());
      user.setCompanies(newCompanies);
    }
    return user;
  }

  private Set<Role> prepareRoles(Set<Role> roles) {
    Set<Role> newRoles = new HashSet<>();
    for( Role emptyRole : roles ) {
      Optional<Role> roleOptional;
      if( emptyRole.getObjectId() != null ) {
        roleOptional = roleRepository.findByNameAndObjectId(emptyRole.getName(), emptyRole.getObjectId());
      } else {
        roleOptional = roleRepository.findByName(emptyRole.getName());
      }
      if( roleOptional.isPresent() ) {
        newRoles.add(roleOptional.get());
      } else {
        throw new BadRequestException("Error: Role not found");
      }
    }
    return newRoles;
  }

  private Set<Company> prepareCompanies(Set<String> companiesId) {
    Set<Company> newCompanies = new HashSet<>();
    for(String companyId : companiesId) {
      Optional<Company> companiesOptional = companyRepository.findById(companyId);
      if( companiesOptional.isPresent() ) {
        newCompanies.add(companiesOptional.get());
      } else {
        throw new BadRequestException("Error: Company not found");
      }
    }
    return newCompanies;
  }
}
