package com.example.javateambot.timer;

import com.example.javateambot.entity.Users;
import com.example.javateambot.service.DogAdoptionService;
import com.example.javateambot.service.TelegramBotService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TelegramBotTimer {
    @Value("${volunteer.chatId}")
    private String vChatId;
    private TelegramBotService telegramBotService;
    private final TelegramBot telegramBot;

    private DogAdoptionService dogAdoptionService;

    public TelegramBotTimer(TelegramBotService telegramBotService, TelegramBot telegramBot, DogAdoptionService dogAdoptionService) {
        this.telegramBotService = telegramBotService;
        this.telegramBot = telegramBot;
        this.dogAdoptionService = dogAdoptionService;
    }

    @Scheduled(cron = "@daily")
    public void run() {
        List<Users> usersList = dogAdoptionService.getUsersByDataReport(LocalDate.now().minusDays(2));
        for (Users u : usersList) {
            telegramBot.execute(new SendMessage(vChatId, u.getFirstName() + " " + u.getLastName() +
                    " " + u.getNumberUser() + "отчет просрочен"));
        }
    }

}
