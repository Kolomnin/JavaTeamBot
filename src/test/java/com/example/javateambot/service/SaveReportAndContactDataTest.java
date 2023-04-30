package com.example.javateambot.service;

import com.example.javateambot.entity.Report;
import com.example.javateambot.repository.ReportRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SaveReportAndContactDataTest {

    @Test
    void saveReport() {
        // Arrange
        SaveReportAndContactData saveReportAndContactData = new SaveReportAndContactData();
        ReportRepository mockReportRepository = mock(ReportRepository.class);
        PhotoService mockPhotoService = mock(PhotoService.class);
        DogAdoptionService mockDogAdoptionService = mock(DogAdoptionService.class);
        TelegramBot mockTelegramBot = mock(TelegramBot.class);
        Report report = new Report();
        saveReportAndContactData.reportRepository = mockReportRepository;
        saveReportAndContactData.photoService = mockPhotoService;
        saveReportAndContactData.dogAdoptionService = mockDogAdoptionService;
        saveReportAndContactData.telegramBot = mockTelegramBot;
        Long chatId = 123456789L;
        String reportMessage = "1. Рацион\n" +
                "2. Поведение\n" +
                "3. Общее самочувствие и привыкание к новому месту +\n" +
                "4. Новые привычки";

        // Act
        saveReportAndContactData.saveReport(reportMessage, chatId);

        // Assert
        verify(mockReportRepository, times(1)).save(any(Report.class));

    }

    @Test
    void saveReport2() {
        String reportMessage =
                "1. Рацион\n" +
                "2. Поведение\n" +
                "3. Общее самочувствие и привыкание к новому месту +\n" +
                "4. Новые привычки";

        String[] fieldsForReport1 = reportMessage.split("\n");
        assertEquals(fieldsForReport1.length,4);
    }
    @Test
    void saveReport3() {
        String reportMessage =
                "1. Рацион\n" +
                        "2. Поведение\n" +
                        "3. Общее самочувствие и привыкание к новому месту +\n" +
                        "4. Новые привычки\n"+
                        "5. Лишняя информация";
        Report report = new Report();
        int lineExpected = 4;
        String[] fieldsForReport1 = reportMessage.split("\n");
        System.out.println("Строчек во входящем сообщении "+fieldsForReport1.length);
        System.out.println("Ожидается строчек  "+ lineExpected);
        assertNotEquals(fieldsForReport1.length,lineExpected);


    }

    //    public void saveReport(String reportMessage, Long chatId) {
//        Report report = new Report();
//        String reportText = reportMessage;
//        String[] fieldsForReport = reportText.split("\\.");
//        System.out.println(fieldsForReport.length);
//        String[] fieldsForReport1 = reportText.split("\n");
//        if (fieldsForReport.length == 4) {
//
//
//            String ration = fieldsForReport[0].trim();
//            report.setRation(ration);
//            String animalBehavior = fieldsForReport[1].trim();
//            report.setAnimalBehavior(animalBehavior);
//            String GeneralWellBeing = fieldsForReport[2].trim();
//            report.setGeneralWellBeing(GeneralWellBeing);
//            String newHabits = fieldsForReport[3].trim();
//            report.setNewHabits(newHabits);
//            report.setAppPhoto(photoService.findPhotoLastId(chatId));
//            report.setUser(dogAdoptionService.findUserByChatId(chatId));
//            report.setDate(LocalDate.now());
//
//            reportRepository.save(report);
//            telegramBot.execute(new SendMessage(chatId, "Ваш отчет сохранен,следующий отчет отправьте завтра"));
//
//        } else if (fieldsForReport1.length == 4) {
//
//            String ration = fieldsForReport1[0].trim();
//            report.setRation(ration);
//            String animalBehavior = fieldsForReport1[1].trim();
//            report.setAnimalBehavior(animalBehavior);
//            String GeneralWellBeing = fieldsForReport1[2].trim();
//            report.setGeneralWellBeing(GeneralWellBeing);
//            String newHabits = fieldsForReport1[3].trim();
//            report.setNewHabits(newHabits);
//            report.setAppPhoto(photoService.findPhotoLastId(chatId));
//            report.setUser(dogAdoptionService.findUserByChatId(chatId));
//            report.setDate(LocalDate.now());
//            reportRepository.save(report);
//            telegramBot.execute(new SendMessage(chatId, "Ваш отчет сохранен,следующий отчет отправьте завтра"));
//
//        }
//    }
    @Test
    public void testSaveReport_InvalidFormat() {
        // Arrange
        SaveReportAndContactData saveReportAndContactData = new SaveReportAndContactData();
        ReportRepository mockReportRepository = mock(ReportRepository.class);
        PhotoService mockPhotoService = mock(PhotoService.class);
        DogAdoptionService mockDogAdoptionService = mock(DogAdoptionService.class);
        TelegramBot mockTelegramBot = mock(TelegramBot.class);
        saveReportAndContactData.reportRepository = mockReportRepository;
        saveReportAndContactData.photoService = mockPhotoService;
        saveReportAndContactData.dogAdoptionService = mockDogAdoptionService;
        saveReportAndContactData.telegramBot = mockTelegramBot;
        Long chatId = 123456789L;
        String reportMessage = "Неправильный формат сообщения";

        // Act
        saveReportAndContactData.saveReport(reportMessage, chatId);

        // Assert
        verify(mockReportRepository, never()).save(any(Report.class));
        verify(mockTelegramBot, times(1)).execute(new SendMessage(chatId, "Неправильный формат сообщения"));
    }
}