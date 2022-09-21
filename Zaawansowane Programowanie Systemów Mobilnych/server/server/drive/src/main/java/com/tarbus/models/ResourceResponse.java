package com.tarbus.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.InputStreamSource;

@Getter
@Setter
@NoArgsConstructor
public class ResourceResponse {
  private InputStreamSource resource;
  private String fileName;
}
