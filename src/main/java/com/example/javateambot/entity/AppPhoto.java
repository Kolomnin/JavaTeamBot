package com.example.javateambot.entity;

import javax.persistence.*;


/**
 * Класс является сущностью объекта AppPhoto,
 * Это фоторафия животного ,которое будет храниться в базе данных
 */
@Entity
@Table(name = "animal_photo")
public class AppPhoto {

    /**
     * Id фотографии
     * хранится в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * fileId фотографии
     * хранится в базе данных
     */
    private String fileId;

    /**
     * data фотографии
     * хранится в базе данных
     */
    private byte[] data;

    /**
     * Метод позволяет получить fileId фотографии
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * Метод позволяет установить байтовое представление фотографии
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * Метод позволяет получить байтовое представление фотографии
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Метод позволяет сохранить байтовое представление фотографии
     */
    public void setData(byte[] data) {
        this.data = data;
    }

}
