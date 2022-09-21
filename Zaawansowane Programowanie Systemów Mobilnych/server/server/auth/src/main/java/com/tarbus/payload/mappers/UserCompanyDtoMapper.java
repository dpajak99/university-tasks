package com.tarbus.payload.mappers;

import com.tarbus.payload.entity.Company;
import com.tarbus.payload.dto.UserCompanyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserCompanyDtoMapper {
  private final FileObjectDtoMapper fileObjectDtoMapper;

  @Autowired
  public UserCompanyDtoMapper(FileObjectDtoMapper fileObjectDtoMapper) {
    this.fileObjectDtoMapper = fileObjectDtoMapper;
  }


  public List<UserCompanyDto> mapListToEntityList(List<Company> companies) {
    List<UserCompanyDto> userCompanyDtoList = new ArrayList<>();
    for(Company company : companies ) {
      userCompanyDtoList.add(mapToEntity(company));
    }
    return userCompanyDtoList;
  }

  public UserCompanyDto mapToEntity(Company company) {
    UserCompanyDto userCompanyDto = new UserCompanyDto();
    userCompanyDto.setId(company.getId());
    userCompanyDto.setName(company.getName());
    userCompanyDto.setWebsite(company.getWebsite());
    userCompanyDto.setPhone(company.getPhone());
    userCompanyDto.setEmail(company.getEmail());
    userCompanyDto.setTimezone(company.getTimezone());
    userCompanyDto.setLang(company.getLang());
    userCompanyDto.setAvatar(fileObjectDtoMapper.mapToEntity(company.getAvatar()));

    return userCompanyDto;
  }
}
