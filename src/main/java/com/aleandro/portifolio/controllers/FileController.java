package com.aleandro.portifolio.controllers;

import com.amazonaws.HttpMethod;
import com.aleandro.portifolio.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping("/upload_img")
    public ResponseEntity<String> generateUrl(@RequestParam String filename) {
        return ResponseEntity.ok(fileService.generatePreSignedUrl(
                filename, HttpMethod.PUT));
    }

    @GetMapping("/my_img")
    public ResponseEntity<String> getUrl(@RequestParam String filename) {
        return ResponseEntity.ok(fileService.generatePreSignedUrl(
                filename, HttpMethod.GET));
    }

}