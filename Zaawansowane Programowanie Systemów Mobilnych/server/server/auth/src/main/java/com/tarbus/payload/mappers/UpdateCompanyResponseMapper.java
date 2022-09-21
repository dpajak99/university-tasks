package com.tarbus.payload.mappers;

import com.tarbus.payload.entity.Company;
import com.tarbus.payload.response.UpdateCompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCompanyResponseMapper {
  private final FileObjectDtoMapper fileObjectDtoMapper;

  @Autowired
  public UpdateCompanyResponseMapper(FileObjectDtoMapper fileObjectDtoMapper) {
    this.fileObjectDtoMapper = fileObjectDtoMapper;
  }


  public UpdateCompanyResponse mapToEntity(Company company) {
    UpdateCompanyResponse updateCompanyResponse = new UpdateCompanyResponse();
    updateCompanyResponse.setId(company.getId());
    updateCompanyResponse.setName(company.getName());
    updateCompanyResponse.setWebsite(company.getWebsite());
    updateCompanyResponse.setPhone(company.getPhone());
    updateCompanyResponse.setEmail(company.getEmail());
    updateCompanyResponse.setTimezone(company.getTimezone());
    updateCompanyResponse.setLang(company.getLang());
    updateCompanyResponse.setAvatar(fileObjectDtoMapper.mapToEntity(company.getAvatar()));

    return updateCompanyResponse;
  }
}
