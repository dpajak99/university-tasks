package com.tarbus.services.impl;

import com.tarbus.AppConfig;
import com.tarbus.models.ResourceResponse;
import com.tarbus.payload.entity.FileObject;
import com.tarbus.payload.entity.User;
import com.tarbus.repositories.jpa.FilesRepository;
import com.tarbus.services.AuthService;
import com.tarbus.services.FilesStorageService;
import com.tarbus.utilities.BufferedImageUtils;
import com.tarbus.utilities.RoleChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  private final Path root;

  private final AppConfig appConfig;
  private final FilesRepository filesRepository;
  private final AuthService authService;

  @Autowired
  public FilesStorageServiceImpl(AppConfig appConfig, FilesRepository filesRepository, AuthService authService) {
    this.appConfig = appConfig;
    this.filesRepository = filesRepository;
    this.authService = authService;
    this.root = Paths.get(appConfig.STORAGE_ABSOLUTE_PATH+ "/uploads");
  }

  @Override
  public FileObject save(MultipartFile file) {
    try {
      String fileName = file.getOriginalFilename();
      System.out.println(fileName);
      String extension = fileName.split("\\.")[fileName.split("\\.").length - 1];
      User user = authService.getSessionUser();
      FileObject fileObject = new FileObject();
      fileObject.setName(fileName);
      fileObject.setExtension(extension);
      fileObject.setUser(user);
      fileObject = filesRepository.save(fileObject);
      Files.copy(file.getInputStream(), this.root.resolve(fileObject.getId() + "." + fileObject.getExtension()));
      return fileObject;
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public ResourceResponse load(String fileId, Integer width, Integer height) {
    try {
      Optional<FileObject> fileObjectOptional = filesRepository.findById(fileId);
      if (fileObjectOptional.isPresent()) {
        FileObject fileObject = fileObjectOptional.get();
        System.out.println(appConfig.STORAGE_ABSOLUTE_PATH + "/uploads/" + fileObject.getParsedName());
        Path file = root.resolve(appConfig.STORAGE_ABSOLUTE_PATH + "/uploads/" + fileObject.getParsedName());
        Resource resource = new UrlResource(file.toUri());

        if (resource.exists() || resource.isReadable()) {
          if (fileObject.isImage() && width != null && height != null) {
            BufferedImage originalImage = ImageIO.read(new File(file.toUri()));
            BufferedImage outputImage = BufferedImageUtils.resize(originalImage, width, height);
            ResourceResponse resourceResponse = new ResourceResponse();
            resourceResponse.setFileName(fileObject.getName());
            resourceResponse.setResource(BufferedImageUtils.toResource(outputImage, fileObject.getExtension()));
            return resourceResponse;
          }

          ResourceResponse resourceResponse = new ResourceResponse();
          resourceResponse.setFileName(fileObject.getName());
          resourceResponse.setResource(resource);
          return resourceResponse;
        } else {
          throw new RuntimeException("Could not read the file!");
        }
      } else {
        throw new RuntimeException("File not exists in database!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}