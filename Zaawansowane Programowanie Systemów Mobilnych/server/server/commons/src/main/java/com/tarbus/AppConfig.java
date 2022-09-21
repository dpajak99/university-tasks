package com.tarbus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
  @Value("${tarbus.app.storage-absolute-path}")
  public String STORAGE_ABSOLUTE_PATH;

  @Value("${tarbus.app.url}")
  public String appURL;

}
