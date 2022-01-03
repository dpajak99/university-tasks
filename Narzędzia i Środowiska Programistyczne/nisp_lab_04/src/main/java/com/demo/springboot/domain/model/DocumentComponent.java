package com.demo.springboot.domain.model;

import com.demo.springboot.domain.dto.UserDataDto;

public interface DocumentComponent {
    void createDocument(UserDataDto userDataDto, String fileDestination);
}
