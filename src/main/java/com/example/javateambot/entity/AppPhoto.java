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
     * data фотографии
     * хранится в базе данных
     */
    @Column(nullable = false)
    private byte[] data;


    @Column(nullable = false)
    private String extension;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
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
