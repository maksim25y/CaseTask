package ru.mudan.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

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
    @Column(name = "created_date")
    private Timestamp date;
    @Column(name = "location")
    private String location;
}
