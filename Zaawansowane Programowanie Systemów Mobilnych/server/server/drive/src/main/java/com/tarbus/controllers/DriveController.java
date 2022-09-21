package com.tarbus.controllers;

import com.tarbus.models.DriveFile;
import com.tarbus.utilities.RoleChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.tarbus.services.DriveService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/drive/")
public class DriveController {

  private final DriveService driveService;
  private final RoleChecker roleChecker;

  @Autowired
  public DriveController(DriveService driveService, RoleChecker roleChecker) {
    this.driveService = driveService;
    this.roleChecker = roleChecker;
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping({"/files"})
  public ResponseEntity<?> getFiles() {
    List<DriveFile> result = driveService.findFilesInRootDirectory();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping({"/files/**"})
  public ResponseEntity<?> getFiles(HttpServletRequest request) {
    String requestURL = request.getRequestURL().toString();
    String moduleName = requestURL.split("/files/")[1];
    if( !roleChecker.isAdmin() && !moduleName.contains("company")) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    List<DriveFile> result = driveService.getFilesInDirectory(moduleName);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
