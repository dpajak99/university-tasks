package com.demo.springboot.rest;

import com.demo.springboot.domain.dto.DocumentDto;
import com.demo.springboot.service.DocumentService;
import com.demo.springboot.service.impl.DocumentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Dominik Pająk
 * 16.12.2020
 */
@Controller
@RequestMapping(value = "/api")
public class DocumentApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentApiController.class);

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "/document/test", method = RequestMethod.GET)
    public ResponseEntity<String> testDocument() {
        LOGGER.info("### Dziala metoda testDocument");
        LOGGER.info("### Sprawdźmy czy działa wstrzykiwanie zależności");

        final String infoAboutDocument = documentService.getInfoAboutDocument();

        LOGGER.info("### Komponent odpowiada: {"+infoAboutDocument+"}");

        return new ResponseEntity<>(infoAboutDocument, HttpStatus.OK);
    }
}




