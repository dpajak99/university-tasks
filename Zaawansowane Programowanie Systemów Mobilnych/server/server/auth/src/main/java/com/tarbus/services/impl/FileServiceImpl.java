package com.tarbus.services.impl;

import com.tarbus.payload.entity.FileObject;
import com.tarbus.repositories.jpa.FilesRepository;
import com.tarbus.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
  private final FilesRepository filesRepository;

  @Autowired
  public FileServiceImpl(FilesRepository filesRepository) {
    this.filesRepository = filesRepository;
  }

  @Override
  public FileObject getFileById(String id) {
    return filesRepository.findById(id).orElse(null);
  }
}
