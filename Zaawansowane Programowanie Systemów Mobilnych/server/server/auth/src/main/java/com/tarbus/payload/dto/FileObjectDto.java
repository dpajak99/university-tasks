package com.tarbus.payload.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FileObjectDto {
  private String id;
  private String name;
  private String extension;
  private Date timestamp;
}
