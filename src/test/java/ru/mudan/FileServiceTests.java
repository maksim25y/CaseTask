package ru.mudan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mudan.dto.FileDTO;
import ru.mudan.repository.FileRepository;
import ru.mudan.services.FileService;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(classes = CaseTaskApplication.class)
public class FileServiceTests {
    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fileRepository;
    @BeforeEach
    public void clearTable(){
        fileRepository.deleteAll();
    }
    @Test
    public void whenLoadFileDTOValidThenReturnId(){
        FileDTO fileDTOForSaving = getDefaultFileDTO();
        assertNotNull(fileService.saveFile(fileDTOForSaving));
        assertEquals(1,fileRepository.findAll().size());
    }
    @Test
    public void whenLoadFileDTOWithNotValidCreatedDateThenReturnNull(){
        FileDTO fileDTOForSaving = getDefaultFileDTO();
        fileDTOForSaving.setCreatedDate("2024-08-26T0s4:25s:50Z");
        assertNull(fileService.saveFile(fileDTOForSaving));
        assertEquals(0,fileRepository.findAll().size());
    }
    @Test
    public void whenLoadFileDTOWithNotValidFileThenReturnNull(){
        FileDTO fileDTOForSaving = getDefaultFileDTO();
        fileDTOForSaving.setFile("c2RzZHNkcw==3s");
        assertNull(fileService.saveFile(fileDTOForSaving));
        assertEquals(0,fileRepository.findAll().size());
    }
    @Test
    public void whenLoadFileDTOWithNotValidFileAndCreatedDateThenReturnNull(){
        FileDTO fileDTOForSaving = getDefaultFileDTO();
        fileDTOForSaving.setCreatedDate("2024-08-26T0s4:25s:50Z");
        fileDTOForSaving.setFile("c2RzZHNkcw==3s");
        assertNull(fileService.saveFile(fileDTOForSaving));
        assertEquals(0,fileRepository.findAll().size());
    }
    @Test
    public void whenGetFileModelByIdAndFileModelPresentThenReturnFileModel(){
        FileDTO fileDTOForSaving = getDefaultFileDTO();
        Long resultId = fileService.saveFile(fileDTOForSaving);
        assertNotNull(resultId);
        assertEquals(1,fileRepository.findAll().size());
        FileDTO fileDTOFromDB = fileService.getFileModelById(resultId);
        assertNotNull(fileDTOFromDB);
        assertEquals(fileDTOForSaving.getTitle(),fileDTOFromDB.getTitle());
        assertEquals(fileDTOForSaving.getDescription(),fileDTOFromDB.getDescription());
        assertEquals(fileDTOForSaving.getCreatedDate(),fileDTOFromDB.getCreatedDate());
        assertEquals(fileDTOForSaving.getFile(),fileDTOFromDB.getFile());
    }
    private FileDTO getDefaultFileDTO(){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setDescription("Test Description");
        fileDTO.setTitle("Test Title");
        fileDTO.setCreatedDate("2024-08-26T04:25:50Z");
        fileDTO.setFile("c2RzZHNkcw==");
        return fileDTO;
    }
}
