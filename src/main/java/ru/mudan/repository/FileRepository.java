package ru.mudan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mudan.models.FileModel;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileModel,Long> {
    List<FileModel>findAllByOrderByCreatedDateAsc();
}
