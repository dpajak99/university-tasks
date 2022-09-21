package com.tarbus.services.impl;

import com.tarbus.models.DriveFile;
import com.tarbus.payload.entity.Company;
import com.tarbus.payload.entity.User;
import com.tarbus.services.AuthService;
import com.tarbus.utilities.RoleChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tarbus.AppConfig;
import com.tarbus.services.DriveService;

import java.io.File;
import java.util.*;

@Service
public class DriveServiceImpl implements DriveService {

  private final AppConfig appConfig;
  private final AuthService authService;
  private final RoleChecker roleChecker;

  @Autowired
  public DriveServiceImpl(AppConfig appConfig, AuthService authService, RoleChecker roleChecker) {
    this.appConfig = appConfig;
    this.authService = authService;
    this.roleChecker = roleChecker;
  }

  @Override
  public List<DriveFile> findFilesInRootDirectory() {
    User user = authService.getSessionUser();
    List<DriveFile> result = new ArrayList<>();
    if( roleChecker.isAdmin() ) {
      result = getFilesInDirectory("");
    } else {
      for (Company company : user.getCompanies()) {
        DriveFile driveFile = new DriveFile();
        driveFile.setName(company.getName());
        driveFile.setPath("company/" + company.getId());
        driveFile.setIsDirectory(true);
        driveFile.setIsFile(false);
        driveFile.setLastModified(Long.parseLong("0"));
        result.add(driveFile);
      }
    }
    return result;
  }

  @Override
  public List<DriveFile> getFilesInDirectory(String directory) {
    List<DriveFile> parsedFiles = new ArrayList<>();
    try {
      final File folder = new File(appConfig.STORAGE_ABSOLUTE_PATH + "/" + directory);
      List<File> files = new ArrayList<>(Arrays.asList(Objects.requireNonNull(folder.listFiles())));

      for (File file : files) {
        DriveFile driveFile = new DriveFile();
        driveFile.setName(file.getName());
        driveFile.setPath( file.getName());
        driveFile.setIsDirectory( file.isDirectory());
        driveFile.setIsFile(file.isFile());
        driveFile.setLastModified(file.lastModified());
        parsedFiles.add(driveFile);
      }

    } catch (Exception e) {
      // do nothing
    }
    return parsedFiles;
  }


}
