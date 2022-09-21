package com.tarbus.controllers.user;


import com.tarbus.exceptions.RequestException;
import com.tarbus.payload.entity.Company;
import com.tarbus.payload.dto.UserCompanyDto;
import com.tarbus.payload.mappers.UserCompanyDtoMapper;
import com.tarbus.payload.request.AddUserToCompanyRequest;
import com.tarbus.services.UserCompanyService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UserCompanyController {

  private final UserCompanyService userCompanyService;
  private final UserCompanyDtoMapper userCompanyDtoMapper;

  @Autowired
  public UserCompanyController(UserCompanyService userCompanyService, UserCompanyDtoMapper userCompanyDtoMapper) {
    this.userCompanyService = userCompanyService;
    this.userCompanyDtoMapper = userCompanyDtoMapper;
  }

  @GetMapping("/user/company")
  @PreAuthorize("hasRole('USER')")
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Successful retrieval of demand", responseContainer = "List<SessionUserCompanyDto>"),
  }
  )
  public ResponseEntity<?> getSessionUserCompanies() {
    try {
      List<Company> companies = userCompanyService.getSessionUserCompanies();
      List<UserCompanyDto> sessionUserCompanies = userCompanyDtoMapper.mapListToEntityList(companies);
      return new ResponseEntity<>(sessionUserCompanies, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @DeleteMapping("/user/company/{id}/leave")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> leaveCompanyBySessionUser(@PathVariable String id) {
    try {
      userCompanyService.leaveCompanyBySessionUser(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @GetMapping("/user/{id}/company")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> getUserCompanies(@PathVariable String id) {
    List<Company> companies = userCompanyService.getUserCompanies(id);
    List<UserCompanyDto> userCompanies = userCompanyDtoMapper.mapListToEntityList(companies);
    return new ResponseEntity<>(userCompanies, HttpStatus.OK);
  }

  @PostMapping("/user/{id}/company")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> addUserToCompany(@PathVariable(name = "id") String userId, @RequestBody AddUserToCompanyRequest addUserToCompanyRequest) {
    try {
      userCompanyService.addUserToCompany(userId, addUserToCompanyRequest.getCompanyId());
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @DeleteMapping("/user/{id}/company")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> removeUserFromCompany(@PathVariable(name = "id") String userId, @RequestParam String companyId) {
    try {
      userCompanyService.removeUserFromCompany(userId, companyId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }
}

