package com.tarbus.utilities;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileScanner {
  public List<String> getResourceFiles(String path) throws IOException {
    List<String> filenames = new ArrayList<>();

    try (
      InputStream in = getResourceAsStream(path);
      BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
      String resource;

      while ((resource = br.readLine()) != null) {
        filenames.add(resource);
      }
    }

    return filenames;
  }

  public InputStream getResourceAsStream(String resource) {
    final InputStream in
      = getContextClassLoader().getResourceAsStream(resource);

    return in == null ? getClass().getResourceAsStream(resource) : in;
  }

  public InputStream getFileByPath(String path) {
    try {
      File file = new File(path);
      return new FileInputStream(file);
    } catch (Exception e) {
      return null;
    }
  }

  public ClassLoader getContextClassLoader() {
    return Thread.currentThread().getContextClassLoader();
  }
}
