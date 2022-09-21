package com.tarbus.services.impl.user;

import com.tarbus.exceptions.BadRequestException;
import com.tarbus.exceptions.UnauthorizedException;
import com.tarbus.payload.entity.Company;
import com.tarbus.payload.entity.CompanyUsers;
import com.tarbus.payload.entity.CompanyUsersId;
import com.tarbus.payload.entity.User;
import com.tarbus.repositories.jpa.CompanyRepository;
import com.tarbus.repositories.jpa.CompanyUsersRepository;
import com.tarbus.repositories.jpa.UserRepository;
import com.tarbus.services.AuthService;
import com.tarbus.services.UserCompanyService;
import com.tarbus.utilities.RoleChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCompanyServiceImpl implements UserCompanyService {
  private final CompanyRepository companyRepository;
  private final CompanyUsersRepository companyUsersRepository;
  private final UserRepository userRepository;
  private final AuthService authService;
  private final RoleChecker roleChecker;

  @Autowired
  public UserCompanyServiceImpl(CompanyRepository companyRepository, AuthService authService, UserRepository userRepository, CompanyUsersRepository companyUsersRepository, RoleChecker roleChecker) {
    this.companyRepository = companyRepository;
    this.authService = authService;
    this.userRepository = userRepository;
    this.companyUsersRepository = companyUsersRepository;
    this.roleChecker = roleChecker;
  }

  @Override
  public List<Company> getSessionUserCompanies() throws UnauthorizedException {
    User currentUser = authService.getSessionUser();
    return companyRepository.getUserCompanies(currentUser.getId());
  }

  @Override
  public List<Company> getUserCompanies(String userId) {
    return companyRepository.getUserCompanies(userId);
  }

  @Override
  public void addUserToCompany(String userId, String companyId) throws BadRequestException, UnauthorizedException {
    CompanyUsers companyUsers = prepareCompanyUser(userId, companyId);
    companyUsersRepository.save(companyUsers);
  }

  @Override
  public void removeUserFromCompany(String userId, String companyId) throws BadRequestException, UnauthorizedException {
    CompanyUsers companyUsers = prepareCompanyUser(userId, companyId);
    companyUsersRepository.delete(companyUsers);
  }

  @Override
  public void leaveCompanyBySessionUser(String companyId) throws BadRequestException, UnauthorizedException {
    User user = authService.getSessionUser();
    CompanyUsers companyUsers = prepareCompanyUser(user.getId(), companyId);
    companyUsersRepository.delete(companyUsers);
  }

  private CompanyUsers prepareCompanyUser(String userId, String companyId) throws BadRequestException, UnauthorizedException {
    List<Company> currentUserCompanies = getSessionUserCompanies();
    Optional<Company> companyOptional = companyRepository.findById(companyId);
    Optional<User> userOptional = userRepository.findById(userId);
    if (!companyOptional.isPresent()) {
      throw new BadRequestException("Error: Company not found");
    }
    if (!userOptional.isPresent()) {
      throw new BadRequestException("Error: User not found");
    }
    // TODO(dominik): Add company roles to add user
    List<String> currentUserCompaniesId = currentUserCompanies.stream().map(Company::getId).collect(Collectors.toList());
    ;
    if (roleChecker.isAdmin() || currentUserCompaniesId.contains(companyId)) {
      CompanyUsersId companyUsersId = new CompanyUsersId(companyOptional.get(), userOptional.get());
      return new CompanyUsers(companyUsersId);
    } else {
      throw new UnauthorizedException();
    }
  }
}
