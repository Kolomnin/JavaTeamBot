package com.example.javateambot.service;

import com.example.javateambot.controllers.UsersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public interface UsersContactService {

    final Logger logger = LoggerFactory.getLogger(UsersContactService.class);

    void addUserContact(Long chatId, String name, int phoneNumber);


}