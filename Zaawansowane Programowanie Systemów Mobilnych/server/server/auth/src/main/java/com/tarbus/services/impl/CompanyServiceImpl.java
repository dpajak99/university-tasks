package com.tarbus.services.impl;

import com.google.common.collect.Lists;
import com.tarbus.exceptions.BadRequestException;
import com.tarbus.exceptions.UnauthorizedException;
import com.tarbus.payload.enums.ERole;
import com.tarbus.payload.mappers.CompanyMapper;
import com.tarbus.payload.request.CreateCompanyRequest;
import com.tarbus.payload.request.UpdateCompanyRequest;
import com.tarbus.utilities.RoleChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tarbus.payload.entity.Company;
import com.tarbus.repositories.jpa.CompanyRepository;
import com.tarbus.services.CompanyService;

import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public class CompanyServiceImpl implements CompanyService {
  private final CompanyRepository companyRepository;
  private final CompanyMapper companyMapper;
  private final RoleChecker roleChecker;

  @Autowired
  public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, RoleChecker roleChecker) {
    this.companyRepository = companyRepository;
    this.companyMapper = companyMapper;
    this.roleChecker = roleChecker;
  }

  @Override
  public List<Company> getAll() {
    return Lists.newArrayList(companyRepository.findAll());
  }

  @Override
  public Company createCompany(CreateCompanyRequest createCompanyRequest) throws UnauthorizedException {
    Company company = companyMapper.mapToEntity(createCompanyRequest);
    List<ERole> requiredRoles =  Lists.newArrayList(ERole.COMPANY_CREATE);
    boolean hasAuthorities = roleChecker.isAdmin() || roleChecker.hasCurrentUserAnyAuthorities(requiredRoles);
    if( hasAuthorities ) {
      return companyRepository.save(company);
    } else {
      throw new UnauthorizedException();
    }
  }

  @Override
  public Company updateCompany(UpdateCompanyRequest updateCompanyRequest) throws BadRequestException, UnauthorizedException {
    Company actualCompany = getCompanyById(updateCompanyRequest.getId());
    Company newCompany = companyMapper.copyWith(actualCompany, updateCompanyRequest);
    List<ERole> requiredRoles =  Lists.newArrayList(ERole.COMPANY_ALL, ERole.COMPANY_UPDATE);
    boolean hasAuthorities = roleChecker.isAdmin() || roleChecker.hasCurrentUserAnyRoles(requiredRoles, newCompany.getId().toString());
    if( hasAuthorities ) {
      return companyRepository.save(newCompany);
    } else {
      throw new UnauthorizedException();
    }
  }

  @Override
  public void removeCompany(String id) throws BadRequestException, UnauthorizedException {
    Company company = getCompanyById(id);
    List<ERole> requiredRoles =  Lists.newArrayList(ERole.COMPANY_ALL);
    boolean hasAuthorities = roleChecker.isAdmin() || roleChecker.hasCurrentUserAnyRoles(requiredRoles, company.getId().toString());
    if( hasAuthorities ) {
      companyRepository.delete(company);
    } else {
      throw new UnauthorizedException();
    }
  }

  @Override
  public Company getCompanyById(String id) throws BadRequestException {
    Optional<Company> companyOptional = companyRepository.findById(id);
    if(companyOptional.isPresent()) {
      return companyOptional.get();
    } else {
      throw new BadRequestException("Error: Company not exists");
    }
  }
}
