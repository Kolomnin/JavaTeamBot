package com.example.javateambot.timer;

import com.example.javateambot.entity.Users;
import com.example.javateambot.service.SaveReportAndContactData;
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
    @Value("${volunteer.chat.id}")
    private String volunteerChatId;
    private TelegramBotService telegramBotService;
    private final TelegramBot telegramBot;

    private SaveReportAndContactData saveReportAndContactData;

    public TelegramBotTimer(TelegramBotService telegramBotService, TelegramBot telegramBot, SaveReportAndContactData saveReportAndContactData) {
        this.telegramBotService = telegramBotService;
        this.telegramBot = telegramBot;
        this.saveReportAndContactData = saveReportAndContactData;
    }


    @Scheduled(cron = "@Daily")
    public void run() {
        List<Users> usersList = saveReportAndContactData.getByDateReport(LocalDate.now().minusDays(2));
        for (Users u : usersList) {
            telegramBot.execute(new SendMessage(volunteerChatId, u.getFirstName() +
                    u.getLastName() + " отчет просрочен"));
        }
    }

}
