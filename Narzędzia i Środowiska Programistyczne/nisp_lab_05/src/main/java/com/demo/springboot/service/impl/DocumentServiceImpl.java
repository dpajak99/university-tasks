package com.demo.springboot.service.impl;

import com.demo.springboot.service.DocumentService;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Override
    public String getInfoAboutDocument() {
        return "Jestem komponentem Springa i można mnie wstrzykiwać";
    }
}
