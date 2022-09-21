package com.tarbus.services;


import com.tarbus.models.ResourceResponse;
import com.tarbus.payload.entity.FileObject;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  /**
   * Saves the provided file in the storage and database
   * @param file - Multipart file from request
   * @return FileObject contains all file details
   */
  FileObject save(MultipartFile file);

  /**
   * Returns file by provided file id
   * @param fileId - file ID
   * @param width - Optional image width
   * @param height - Optional image height
   * @return file as resource
   */
  ResourceResponse load(String fileId, Integer width, Integer height);
}