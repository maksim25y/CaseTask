package ru.mudan.services;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mudan.dto.FileDTO;
import ru.mudan.models.FileModel;
import ru.mudan.repository.FileRepository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        try{
            ZonedDateTime localDateTime = ZonedDateTime.parse(dateInFileDTO);
            fileModelForSaving.setCreatedDate(localDateTime);
            fileModelForSaving.setFileBytes(convertBase64eToBytes(fileDTO.getFile()));
        }catch (Exception e){
            return null;
        }
        FileModel fileModel = fileRepository.save(fileModelForSaving);
        return fileModel.getId();
    }
    public FileDTO getFileModelById(Long id){
        Optional<FileModel>optionalFileModel = fileRepository.findById(id);
        return optionalFileModel.map(this::convertFileModelToFileDTO).orElse(null);
    }
    public List<FileDTO>getAllFilesSortByDate(boolean sortByDate){
        if(sortByDate){
            return fileRepository.findAllByOrderByCreatedDateAsc()
                    .stream()
                    .map(this::convertFileModelToFileDTO).collect(Collectors.toList());
        }
        return fileRepository.findAll().stream()
                .map(this::convertFileModelToFileDTO).collect(Collectors.toList());
    }
    public List<FileDTO>getAllFilesWithPagination(Integer count,Integer number,boolean sortByCreatedDate){
        List<FileDTO>fileDTOList = getAllFilesSortByDate(sortByCreatedDate);
        List<FileDTO>result = new ArrayList<>();
        int pos=count*(number-1);
        int sizeOfList = fileDTOList.size();
        while (pos<sizeOfList&&count!=0){
            result.add(fileDTOList.get(pos));
            pos++;
            count--;
        }
        return result;
    }
    private FileDTO convertFileModelToFileDTO(FileModel fileModel){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setTitle(fileModel.getTitle());
        fileDTO.setDescription(fileModel.getDescription());
        fileDTO.setCreatedDate(fileModel.getCreatedDate().format(DateTimeFormatter.ISO_INSTANT));
        fileDTO.setFile(convertBytesToBase64(fileModel.getFileBytes()));
        return fileDTO;
    }
    private String convertBytesToBase64(byte[]bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }
    private byte[]convertBase64eToBytes(String base64){
        try {
            return Base64.getDecoder().decode(base64);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Provided string is not a valid Base64 encoded string");
        }
    }
}
