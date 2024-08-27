package ru.mudan.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "files")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column
    private String description;
    @Column(name = "created_date",columnDefinition = "TIMESTAMP")
    private ZonedDateTime createdDate;
    @Column(name = "file",columnDefinition = "bytea")
    private byte[] fileBytes;
}
