package com.example.javateambot.timer;

import com.example.javateambot.service.TelegramBotService;
import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Component;

@Component
public class TelegramBotTimer {
    private TelegramBotService telegramBotService;
    private final TelegramBot telegramBot;

    public TelegramBotTimer(TelegramBotService telegramBotService, TelegramBot telegramBot) {
        this.telegramBotService = telegramBotService;
        this.telegramBot = telegramBot;
    }





}
