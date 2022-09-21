package com.tarbus.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
public class DriveFile {
  private String name;
  private String path;
  private Boolean isFile;
  private Boolean isDirectory;
  private Long lastModified;
}
