package com.example.javateambot.service;

import org.springframework.stereotype.Component;

@Component
public interface UsersContactService {
    void addUserContact(Long chatId, String name, int phoneNumber);


}