package ru.mudan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mudan.dto.FileDTO;
import ru.mudan.services.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
    private final FileService fileService;
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    @GetMapping
    public ResponseEntity<Object>saveImage(@RequestBody FileDTO fileDTO){
        System.out.println(fileDTO);
        return new ResponseEntity<>(fileService.saveFile(fileDTO),HttpStatus.OK);
    }
}
