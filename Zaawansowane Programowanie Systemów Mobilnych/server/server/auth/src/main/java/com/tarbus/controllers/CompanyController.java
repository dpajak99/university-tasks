package com.tarbus.controllers;

import com.tarbus.exceptions.RequestException;
import com.tarbus.payload.entity.Company;
import com.tarbus.payload.mappers.UpdateCompanyResponseMapper;
import com.tarbus.payload.request.CreateCompanyRequest;
import com.tarbus.payload.request.UpdateCompanyRequest;
import com.tarbus.payload.response.UpdateCompanyResponse;
import com.tarbus.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class CompanyController {
  private final UpdateCompanyResponseMapper updateCompanyResponseMapper;
  private final CompanyService companyService;

  @Autowired
  public CompanyController( UpdateCompanyResponseMapper updateCompanyResponseMapper, CompanyService companyService) {
    this.updateCompanyResponseMapper = updateCompanyResponseMapper;
    this.companyService = companyService;
  }

  @GetMapping("/company")
  public ResponseEntity<?> getAllCompanies() {
    List<Company> companies = companyService.getAll();
    return new ResponseEntity<>(companies, HttpStatus.OK);
  }

  @GetMapping("/company/{id}")
  public ResponseEntity<?> getCompanyById(@PathVariable String id) {
    try {
    Company company = companyService.getCompanyById(id);
    return new ResponseEntity<>(company, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @PutMapping("/company")
  public ResponseEntity<?> createCompany(@RequestBody CreateCompanyRequest createCompanyRequest) {
    try {
      Company company = companyService.createCompany(createCompanyRequest);
      UpdateCompanyResponse updatedCompanyResponse = updateCompanyResponseMapper.mapToEntity(company);
      return new ResponseEntity<>(updatedCompanyResponse, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @PostMapping("/company")
  public ResponseEntity<?> updateCompany(@RequestBody UpdateCompanyRequest updateCompanyRequest) {
    try {
      Company company = companyService.updateCompany(updateCompanyRequest);
      UpdateCompanyResponse updatedCompanyResponse = updateCompanyResponseMapper.mapToEntity(company);
      return new ResponseEntity<>(updatedCompanyResponse, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @DeleteMapping("/company/{id}")
  public ResponseEntity<?> removeCompany(@PathVariable String id) {
    try {
      companyService.removeCompany(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }
}
