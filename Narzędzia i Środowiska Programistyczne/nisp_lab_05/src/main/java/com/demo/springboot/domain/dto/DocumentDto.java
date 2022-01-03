package com.demo.springboot.domain.dto;

public class DocumentDto {
    private String name;
    private String extension;
    private double size;

    public DocumentDto(){};

    public DocumentDto(String name, String extension, double size) {
        this.name = name;
        this.extension = extension;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public double getSize() {
        return size;
    }
}
