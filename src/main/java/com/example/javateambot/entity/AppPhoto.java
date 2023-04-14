package com.example.javateambot.entity;

import javax.persistence.*;

@Entity
@Table(name = "animal_photo")
public class AppPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Размер файла
     */
    private Long fileSize;


    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }



    private byte[] fileAsArrayOfBytes;

    public byte[] getFileAsArrayOfBytes() {
        return fileAsArrayOfBytes;
    }

    public void setFileAsArrayOfBytes(byte[] fileAsArrayOfBytes) {
        this.fileAsArrayOfBytes = fileAsArrayOfBytes;
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

}
