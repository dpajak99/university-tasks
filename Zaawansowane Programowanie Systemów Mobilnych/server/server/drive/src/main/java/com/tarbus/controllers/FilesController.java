package com.tarbus.controllers;

import com.tarbus.exceptions.RequestException;
import com.tarbus.models.ResourceResponse;
import com.tarbus.models.ResponseMessage;
import com.tarbus.payload.entity.FileObject;
import com.tarbus.payload.mappers.FileObjectDtoMapper;
import com.tarbus.services.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin
@RequestMapping("/drive/")
public class FilesController {

  private final FilesStorageService storageService;
  private final FileObjectDtoMapper fileObjectDtoMapper;

  @Autowired
  public FilesController(FilesStorageService storageService, FileObjectDtoMapper fileObjectDtoMapper) {
    this.storageService = storageService;
    this.fileObjectDtoMapper = fileObjectDtoMapper;
  }


  @PostMapping("/upload")
  public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
    System.out.println("uploadFile - " + file);
    try {
      FileObject uploadedFile = storageService.save(file);
      return new ResponseEntity<>(fileObjectDtoMapper.mapToEntity(uploadedFile), HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    } catch (Exception e) {
      String message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/uploads/{filename:.+}")
  @ResponseBody
  public ResponseEntity<InputStreamSource> getFile(@PathVariable String filename, @RequestParam(required=false) Integer width, @RequestParam(required=false) Integer height) {
    ResourceResponse file = storageService.load(filename, width, height);
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"").body(file.getResource());
  }
}