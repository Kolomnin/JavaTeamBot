package com.example.javateambot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact_information")
public class ContactInformation {

    @Id
    @Column(name = "chatId", nullable = false)
    private String chatId;

    public String getChatId() {
        return chatId;
    }


    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    private String phoneNumber;

    private String name, surname;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
