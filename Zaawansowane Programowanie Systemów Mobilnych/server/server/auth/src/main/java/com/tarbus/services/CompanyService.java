package com.tarbus.services;

import com.tarbus.exceptions.BadRequestException;
import com.tarbus.exceptions.UnauthorizedException;
import com.tarbus.payload.entity.Company;
import com.tarbus.payload.request.CreateCompanyRequest;
import com.tarbus.payload.request.UpdateCompanyRequest;

import java.util.List;

public interface CompanyService {
  List<Company> getAll();
  Company getCompanyById(String id) throws BadRequestException;
  Company createCompany(CreateCompanyRequest createCompanyRequest) throws UnauthorizedException;
  Company updateCompany(UpdateCompanyRequest updateCompanyRequest) throws BadRequestException, UnauthorizedException;
  void removeCompany(String id) throws BadRequestException, UnauthorizedException;
}
