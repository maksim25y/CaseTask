package ru.mudan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mudan.dto.FileDTO;
import ru.mudan.models.FileModel;
import ru.mudan.repository.FileRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Optional;

@Service
@Transactional
public class FileService {
    private final FileRepository fileRepository;
    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }
    public Long saveFile(FileDTO fileDTO){
        FileModel fileModelForSaving = new FileModel();
        fileModelForSaving.setTitle(fileDTO.getTitle());
        fileModelForSaving.setDescription(fileDTO.getDescription());
        String dateInFileDTO = fileDTO.getCreatedDate();
        LocalDateTime localDateTime = ZonedDateTime.parse(dateInFileDTO).toLocalDateTime();
        fileModelForSaving.setCreatedDate(localDateTime);
        byte[]bytes = convertBase64eToBytes(fileDTO.getFile());
        fileModelForSaving.setFileBytes(bytes);
        FileModel fileModel = fileRepository.save(fileModelForSaving);
        System.out.println(fileModel);
        return fileModel.getId();
    }
    private byte[]convertBase64eToBytes(String base64){
        System.out.println(base64);
        return Base64.getDecoder().decode(base64);
    }
}
