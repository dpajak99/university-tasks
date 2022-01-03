package com.demo.springboot.domain.dto;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FileData {
    private String fileName;
    private Long size;
    private ZonedDateTime creationDate;

    public FileData(String fileName, Long size, ZonedDateTime creationDate) {
        this.fileName = fileName;
        this.size = size;
        this.creationDate = creationDate;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getSize() {
        return size;
    }

    public String getCreationDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(creationDate);
    }
}
