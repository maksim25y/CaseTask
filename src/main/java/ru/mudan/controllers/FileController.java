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
            return new ResponseEntity<>("Data from JSON an incorrect format",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultId,HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<Object>getFile(@RequestParam Long id){
        FileDTO fileDTO = fileService.getFileModelById(id);
        if(fileDTO==null){
            return new ResponseEntity<>(String.format("File with id %s not found",id),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fileService.getFileModelById(id),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<Object>getAllFiles(@RequestParam(required = false, defaultValue = "false") Boolean sortByCreatedDate,
                                             @RequestParam(required = false)Integer count,
                                             @RequestParam(required = false)Integer number){
        if(number!=null&&count!=null){
            return new ResponseEntity<>(fileService.getAllFilesWithPagination(count,number,sortByCreatedDate),HttpStatus.OK);
        }
        return new ResponseEntity<>(fileService.getAllFilesSortByDate(sortByCreatedDate), HttpStatus.OK);
    }
}
