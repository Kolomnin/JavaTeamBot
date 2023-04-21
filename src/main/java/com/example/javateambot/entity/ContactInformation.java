package com.example.javateambot.entity;

import javax.persistence.*;

@Entity
@Table(name = "contact_information")
public class ContactInformation {



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Long chatId;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)

    private String LastName;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}

