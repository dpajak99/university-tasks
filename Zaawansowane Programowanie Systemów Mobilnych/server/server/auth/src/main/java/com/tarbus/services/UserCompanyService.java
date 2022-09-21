package com.tarbus.services;

import com.tarbus.exceptions.BadRequestException;
import com.tarbus.exceptions.UnauthorizedException;
import com.tarbus.payload.entity.Company;

import java.util.List;

public interface UserCompanyService {
  List<Company> getSessionUserCompanies() throws UnauthorizedException;
  void leaveCompanyBySessionUser(String companyId) throws BadRequestException, UnauthorizedException;
    
  List<Company> getUserCompanies(String userId);

  void addUserToCompany(String userId, String companyId) throws BadRequestException, UnauthorizedException;
  void removeUserFromCompany(String userId, String companyId) throws BadRequestException, UnauthorizedException;

}
