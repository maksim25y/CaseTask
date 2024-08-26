package ru.mudan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/create")
    public ResponseEntity<Object>saveFile(@RequestBody FileDTO fileDTO){
        System.out.println(fileDTO);
        return new ResponseEntity<>(fileService.saveFile(fileDTO),HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<Object>saveFile(@RequestParam Long id){
        return new ResponseEntity<>(fileService.getFileModelById(id),HttpStatus.OK);
    }
}
