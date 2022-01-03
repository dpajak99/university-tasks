package com.demo.springboot.domain.dto;

/** Klasa odpowiedzialna za przechowywanie informacji o bledach w aplikacji */
public class ErrorDto {
    private String errorMessage;

    public ErrorDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
