package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.FileObjectDto;
import com.tarbus.payload.entity.FileObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FileObjectDtoMapper {
  public FileObjectDto mapToEntity(FileObject fileObject) {
    if( fileObject == null ) {
      return null;
    }
    FileObjectDto fileObjectDto = new FileObjectDto();
    fileObjectDto.setId(fileObject.getId());
    fileObjectDto.setName(fileObject.getName());
    fileObjectDto.setExtension(fileObject.getExtension());
    fileObjectDto.setTimestamp(fileObject.getTimestamp());
    return fileObjectDto;
  }
}
