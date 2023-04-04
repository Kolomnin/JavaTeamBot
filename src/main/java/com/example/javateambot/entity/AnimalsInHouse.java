package com.example.javateambot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Класс является сущностью объекта AnimalsInHouse,
 * Это сущность животного ,которое будет храниться в базе данных
 */
@Entity
@Table(name = "animalsInHouse")
public class AnimalsInHouse {

    /**
     * Id животного
     * хранится в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnimal;
    /**
     * Имя животного
     * хранится в базе данных
     */
    @Column(nullable = false)
    private String nameAnimal;
    /**
     * Номер телефона владельца
     * хранится в базе данных
     */
    @Column(nullable = false)
    private String numberForOwner;
    /**
     * Дата конца испытательного срока у хозаина,
     * хранится в базе данных
     */
    @Column(nullable = false)
    private LocalDate lastDateProbationPeriod; // конец испытательного срока
    /**
     * Дата когда был отправлен последний отчет о состоянии животного,
     * который находится у хозаина,
     * хранится в базе данных
     */
    @Column(nullable = false)
    private LocalDate lastReportDate;

    /**
     * Путь к файлу,изображению
     */
    private String filePath;

    /**
     * Размер файла
     */
    private long fileSize;

    /**
     * Тип файла
     */
    private String mediaType;

    /**
     * Текст отчета о состоянии питомца, который отправляется ежедневно
     */
    @Column(nullable = false)
    private String lastText;

    /**
     * Метод позволяет получить Id животного
     */
    public long getIdAnimal() {
        return idAnimal;
    }
    /**
     * Метод позволяет установить Id животного
     */
    public void setIdAnimal(long idAnimal) {
        this.idAnimal = idAnimal;
    }
    /**
     * Метод позволяет поулчить имя животного
     */
    public String getNameAnimal() {
        return nameAnimal;
    }
    /**
     * Метод позволяет установить имя животного
     */
    public void setNameAnimal(String nameAnimal) {
        this.nameAnimal = nameAnimal;
    }
    /**
     * Метод позволяет получить номер телефона владельца
     */
    public String getNumberForOwner() {
        return numberForOwner;
    }
    /**
     * Метод позволяет установить номер телефона владельца
     */
    public void setNumberForOwner(String numberForOwner) {
        this.numberForOwner = numberForOwner;
    }
    /**
     * Метод позволяет получить дату  последнего дня испытательного срока
     */
    public LocalDate getLastDateProbationPeriod() {
        return lastDateProbationPeriod;
    }
    /**
     * Метод позволяет установить дату последнего дня испытательного срока
     */
    public void setLastDateProbationPeriod(LocalDate lastDateProbationPeriod) {
        this.lastDateProbationPeriod = lastDateProbationPeriod;
    }
    /**
     * Метод позволяет получить дату когда был отправлен последний отчет
     */
    public LocalDate getLastReportDate() {
        return lastReportDate;
    }
    /**
     * Метод позволяет установить дату когда был отправлен последний отчет
     */
    public void setLastReportDate(LocalDate lastReportDate) {
        this.lastReportDate = lastReportDate;
    }
    /**
     * Метод позволяет получить путь до файла
     */
    public String getFilePath() {
        return filePath;
    }
    /**
     * Метод позволяет установить путь до файла
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Метод позволяет получить размер  файла
     */
    public long getFileSize() {
        return fileSize;
    }
    /**
     * Метод позволяет установить размер  файла
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    /**
     * Метод позволяет получить тип  файла
     */
    public String getMediaType() {
        return mediaType;
    }
    /**
     * Метод позволяет установить тип  файла
     */
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    /**
     * Метод позволяет получить текст отчета
     */
    public String getLastText() {
        return lastText;
    }
    /**
     * Метод позволяет установить текст отчета
     */
    public void setLastText(String lastText) {
        this.lastText = lastText;
    }
}
