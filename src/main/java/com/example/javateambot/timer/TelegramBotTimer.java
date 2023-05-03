package com.example.javateambot.timer;

import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.CatAdoptionRepository;
import com.example.javateambot.service.CatAdoptionService;
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

    private CatAdoptionRepository catAdoptionRepository;

    public TelegramBotTimer(TelegramBotService telegramBotService, TelegramBot telegramBot,
                            SaveReportAndContactData saveReportAndContactData, CatAdoptionRepository catAdoptionRepository) {
        this.telegramBotService = telegramBotService;
        this.telegramBot = telegramBot;
        this.saveReportAndContactData = saveReportAndContactData;
        this.catAdoptionRepository = catAdoptionRepository;

    }


    @Scheduled(cron = "@Daily")
    public void run() {
        List<Users> usersList = saveReportAndContactData.getByDateReport(LocalDate.now().minusDays(2));
        for (Users u : usersList) {
            telegramBot.execute(new SendMessage(volunteerChatId, u.getFirstName() +
                    u.getLastName() + " отчет просрочен"));
        }
    }

//    @Scheduled(cron = "0 0 * * * *")
//    public void congratsWithSuccessAdoptionCat() {
//        var probationDate = catAdoptionRepository.findByLastDateProbationPeriod(LocalDate.now().trunc)
//    }
}
