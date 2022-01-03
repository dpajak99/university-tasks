package com.demo.springboot.domain.model;

import com.demo.springboot.domain.dto.FileData;

import java.util.List;

public interface Resource {
    String fileName = "files.csv";

    void saveOne(FileData fileData, String path);

    List<FileData> findAll(String path);
}
