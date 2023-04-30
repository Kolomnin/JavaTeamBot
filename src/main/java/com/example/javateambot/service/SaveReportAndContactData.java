package com.example.javateambot.service;

import com.example.javateambot.entity.ContactInformation;
import com.example.javateambot.entity.Report;
import com.example.javateambot.repository.ContactInformationRepository;
import com.example.javateambot.repository.PhotoRepository;
import com.example.javateambot.repository.ReportRepository;
import com.example.javateambot.repository.UsersRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaveReportAndContactData {

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    TelegramBot telegramBot;

    @Autowired
    PhotoService photoService;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    ContactInformationRepository contactInformationRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired DogAdoptionService dogAdoptionService;

    public void saveReport(String reportMessage, Long chatId) {
        Report report = new Report();
        String reportText = reportMessage;
        String[] fieldsForReport = reportText.split("\\.");
        System.out.println(fieldsForReport.length);
        String[] fieldsForReport1 = reportText.split("\n");
        if (fieldsForReport.length == 4) {


            String ration = fieldsForReport[0].trim();
            report.setRation(ration);
            String animalBehavior = fieldsForReport[1].trim();
            report.setAnimalBehavior(animalBehavior);
            String GeneralWellBeing = fieldsForReport[2].trim();
            report.setGeneralWellBeing(GeneralWellBeing);
            String newHabits = fieldsForReport[3].trim();
            report.setNewHabits(newHabits);
            report.setAppPhoto(photoService.findPhotoLastId(chatId));
            report.setUser(usersRepository.findByChatId(chatId));
            report.setDate(LocalDate.now());

            reportRepository.save(report);
            telegramBot.execute(new SendMessage(chatId, "Ваш отчет сохранен,следующий отчет отправьте завтра"));

        } else if (fieldsForReport1.length == 4) {

            String ration = fieldsForReport1[0].trim();
            report.setRation(ration);
            String animalBehavior = fieldsForReport1[1].trim();
            report.setAnimalBehavior(animalBehavior);
            String GeneralWellBeing = fieldsForReport1[2].trim();
            report.setGeneralWellBeing(GeneralWellBeing);
            String newHabits = fieldsForReport1[3].trim();
            report.setNewHabits(newHabits);
            report.setAppPhoto(photoService.findPhotoLastId(chatId));
            report.setUser(usersRepository.findByChatId(chatId));
            report.setDate(LocalDate.now());
            reportRepository.save(report);
            telegramBot.execute(new SendMessage(chatId, "Ваш отчет сохранен,следующий отчет отправьте завтра"));

        }
    }

    public void saveContactData(String message, Long chatId) {

        ContactInformation contactInformation = new ContactInformation();
        String messageText = message;
        String[] fields = messageText.split(" ");
        String[] fields1 = messageText.split("\n");

        if (fields.length == 3) {

            String firstName = fields[0].trim();
            contactInformation.setFirstname(firstName);
            String lastName = fields[1].trim();
            contactInformation.setLastName(lastName);
            String phoneNumber = fields[2].trim();
            contactInformation.setPhoneNumber(phoneNumber);
            contactInformation.setChatId(chatId);
            contactInformationRepository.save(contactInformation);
            telegramBot.execute(new SendMessage(chatId, "Ваши данные сохранены"));

        } else if (fields1.length == 3) {

            String firstName = fields1[0].trim();
            contactInformation.setFirstname(firstName);
            String lastName = fields1[1].trim();
            contactInformation.setLastName(lastName);
            String phoneNumber = fields1[2].trim();
            contactInformation.setPhoneNumber(phoneNumber);
            contactInformation.setChatId(chatId);
            contactInformationRepository.save(contactInformation);
            telegramBot.execute(new SendMessage(chatId, "Ваши данные сохранены"));

        }
    }


}
