package com.example.javateambot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="animals")
public class Animal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idAnimal;

    @Column(nullable = false)
    private String nameAnimal;

    @Column(nullable = false)
    private String numberForOwner;

    @Column(nullable = false)
    private LocalDate lastDateProbationPeriod; // конец испытательного срока

    @Column(nullable = false)
    private LocalDate lastReportDate;

    private String filePath;
    private long fileSize;
    private String mediaType;

    @Column(nullable = false)
    private String lastText;

    public long getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(long idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNameAnimal() {
        return nameAnimal;
    }

    public void setNameAnimal(String nameAnimal) {
        this.nameAnimal = nameAnimal;
    }

    public String getNumberForOwner() {
        return numberForOwner;
    }

    public void setNumberForOwner(String numberForOwner) {
        this.numberForOwner = numberForOwner;
    }

    public LocalDate getLastDateProbationPeriod() {
        return lastDateProbationPeriod;
    }

    public void setLastDateProbationPeriod(LocalDate lastDateProbationPeriod) {
        this.lastDateProbationPeriod = lastDateProbationPeriod;
    }

    public LocalDate getLastReportDate() {
        return lastReportDate;
    }

    public void setLastReportDate(LocalDate lastReportDate) {
        this.lastReportDate = lastReportDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getLastText() {
        return lastText;
    }

    public void setLastText(String lastText) {
        this.lastText = lastText;
    }
}
