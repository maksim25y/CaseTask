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
        Long resultId = fileService.saveFile(fileDTO);
        if(resultId==null){
            return new ResponseEntity<>("Error of encode file",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultId,HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<Object>saveFile(@RequestParam Long id){
        FileDTO fileDTO = fileService.getFileModelById(id);
        if(fileDTO==null){
            return new ResponseEntity<>(String.format("File with id %s not found",id),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fileService.getFileModelById(id),HttpStatus.OK);
    }
}
