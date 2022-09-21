package com.tarbus.payload.mappers;

import com.tarbus.payload.entity.Company;
import com.tarbus.payload.entity.FileObject;
import com.tarbus.payload.request.CreateCompanyRequest;
import com.tarbus.payload.request.UpdateCompanyRequest;
import com.tarbus.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CompanyMapper {
  private final FileService fileService;

  @Autowired
  public CompanyMapper(FileService fileService) {
    this.fileService = fileService;
  }

  public Company copyWith(Company company, UpdateCompanyRequest updateCompanyRequest) {
    if (updateCompanyRequest.getId() != null) {
      company.setName(updateCompanyRequest.getName());
    }
    if (updateCompanyRequest.getWebsite() != null) {
      company.setWebsite(updateCompanyRequest.getWebsite());
    }
    if (updateCompanyRequest.getPhone() != null) {
      company.setPhone(updateCompanyRequest.getPhone());
    }
    if (updateCompanyRequest.getEmail() != null) {
      company.setEmail(updateCompanyRequest.getEmail());
    }
    if (updateCompanyRequest.getTimezone() != null) {
      company.setTimezone(updateCompanyRequest.getTimezone());
    }
    if (updateCompanyRequest.getLang() != null) {
      company.setLang(updateCompanyRequest.getLang());
    }
    if (updateCompanyRequest.getAvatarId() != null) {
      FileObject fileObject = fileService.getFileById(updateCompanyRequest.getAvatarId());
      company.setAvatar(fileObject);
    }

    return company;
  }

  public Company mapToEntity(CreateCompanyRequest createCompanyRequest) {
    Company company = new Company();
    company.setId(UUID.randomUUID().toString());
    company.setName(createCompanyRequest.getName());
    company.setWebsite(createCompanyRequest.getWebsite());
    company.setPhone(createCompanyRequest.getPhone());
    company.setEmail(createCompanyRequest.getEmail());
    company.setTimezone(createCompanyRequest.getTimezone());
    company.setLang(createCompanyRequest.getLang());
    FileObject fileObject = fileService.getFileById(createCompanyRequest.getAvatar());
    company.setAvatar(fileObject);

    return company;
  }
}
