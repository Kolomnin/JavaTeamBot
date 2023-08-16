package com.example.javateambot.timer;

import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.AdoptedCats;
import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.CatAdoptionRepository;
import com.example.javateambot.repository.DogAdoptionRepository;
import com.example.javateambot.repository.DogsInShelterRepository;
import com.example.javateambot.service.SaveReportAndContactData;
import com.example.javateambot.service.TelegramBotService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@EnableScheduling
public class TelegramBotTimer {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotTimer.class);

    @Value("${volunteer.chat.id}")
    private String volunteerChatId;
    private TelegramBotService telegramBotService;
    private final TelegramBot telegramBot;

    private SaveReportAndContactData saveReportAndContactData;
    private DogAdoptionRepository dogAdoptionRepository;
    private CatAdoptionRepository catAdoptionRepository;
    private final DogsInShelterRepository dogsInShelterRepository;

    public TelegramBotTimer(TelegramBotService telegramBotService, TelegramBot telegramBot,
                            SaveReportAndContactData saveReportAndContactData, CatAdoptionRepository catAdoptionRepository, DogAdoptionRepository dogAdoptionRepository,
                            DogsInShelterRepository dogsInShelterRepository) {
        this.telegramBotService = telegramBotService;
        this.telegramBot = telegramBot;
        this.saveReportAndContactData = saveReportAndContactData;
        this.catAdoptionRepository = catAdoptionRepository;
        this.dogAdoptionRepository = dogAdoptionRepository;
        this.dogsInShelterRepository = dogsInShelterRepository;
    }

    /**
     * Метод отправляет волонтеру уведомление о том что отчет не присылался
     */
    @Scheduled(cron = "@Daily")
    public void run() {
        List<Users> usersList = saveReportAndContactData.getByDateReport(LocalDate.now().minusDays(2));
        for (Users u : usersList) {
            telegramBot.execute(new SendMessage(volunteerChatId, u.getFirstName() +
                    u.getLastName() + " отчет просрочен"));
        }
    }

    /**
     * Метод отправляет владельцу поздравление о том что испытательный период пройден
     */
    @Scheduled(cron = "@Daily")
    public void congratsWithSuccessAdoptionCat() {
        LocalDate today = LocalDate.now();
        String congratsMessage = "Поздравляем,ваш период усыновления пройден, желаем вам хорошей " +
                "совместной жизни с вашим питомцем ";
        AdoptedCats a1 = catAdoptionRepository.findByLastDateProbationPeriod(today);

        LocalDate dateLastProbationPeriod = a1.getLastDateProbationPeriod();

        if (dateLastProbationPeriod.equals(today)) {
            Long chatId = a1.getUsers().getChatId();
            telegramBot.execute(new SendMessage(chatId, congratsMessage));
        }
    }




}
