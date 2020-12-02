package com.demo.springboot.service;

import com.demo.springboot.domain.dto.UserDataDto;
import com.demo.springboot.domain.dto.FileData;

import java.util.List;

public interface FileService {

    FileData createFile(UserDataDto userDataDto, String path);

    List<FileData> findAll(String path);
}
