package com.example.javateambot.service;

import com.example.javateambot.entity.ContactInformation;
import com.example.javateambot.entity.Report;
import com.example.javateambot.repository.ContactInformationRepository;
import com.example.javateambot.repository.ReportRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotService {

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    ContactInformationRepository contactInformationRepository;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    private PhotoService photoService;

    public InlineKeyboardButton saveInfo() {  // метод записи данных
        InlineKeyboardButton button = new InlineKeyboardButton("записать данные");
        button.callbackData("записать данные");
        return button;
    }

    public void takeDogFromShelter(Long chatId) {  // кнопки этапа 2, кейсы между 2 и 3
        SendMessage message = new SendMessage(chatId, "Приветствует в нашем приюте");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Правила знакомства с собакой до того, как можно забрать ее из приюта.");
        button1.callbackData("Правила знакомства");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Список документов, необходимых для того, чтобы взять собаку из приюта.");
        button2.callbackData("Список документов");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Список рекомендаций по транспортировке животного.");
        button3.callbackData("транспортировка животного");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для щенка.");
        button4.callbackData("дома для щенка");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для взрослой собаки.");
        button5.callbackData("дома для собаки");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для собаки с ограниченными возможностями (зрение, передвижение).");
        button6.callbackData("дома для собаки с ограничениями");
        InlineKeyboardButton button7 = new InlineKeyboardButton("Советы кинолога по первичному общению с собакой");
        button7.callbackData("советы кинолога");
        InlineKeyboardButton button8 = new InlineKeyboardButton("Рекомендации по проверенным кинологам для дальнейшего обращения к ним.");
        button8.callbackData("список кинологов");
        InlineKeyboardButton button9 = new InlineKeyboardButton("Список причин, почему могут отказать и не дать забрать собаку из приюта.");
        button9.callbackData("список причин для отказа");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);
        keyboard.addRow(button4);
        keyboard.addRow(button5);
        keyboard.addRow(button6);
        keyboard.addRow(button7);
        keyboard.addRow(button8);
        keyboard.addRow(button9);
        keyboard.addRow(saveInfo());
        keyboard.addRow(helpVolunteers());
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }

    public void sendReport(Long chatId) {  // кнопки этапа 3, кейсы между 2 и 3
        SendMessage message = new SendMessage(chatId, "Приветствует в нашем приюте");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Форма ежедневного отчёта");
        button1.callbackData("Форма ежедневного отчёта");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Прислать ежедневный отчёт");
        button2.callbackData("принимаем отчет");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(helpVolunteers());
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }

    public void firstMenu(Long chatId) { // меню начальное, кейсы 1/2/3
        SendMessage helloMessage = new SendMessage(chatId, "Привет,  тут должна быть информация о боте. Выберите интересующий пункт из меню: ");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Узнать информацию о приюте!");
        button1.callbackData("1");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Как взять собаку из приюта?");
        button2.callbackData("2");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце");
        button3.callbackData("3");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера");
        button4.callbackData("позвать волонтера");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1, button2);
        keyboard.addRow(button3, button4);
        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }

    public void shelterInfo(Long chatId) { // кнопки этапа 1, кейсы между 1 и 2

        SendMessage message = new SendMessage(chatId, "Приветствует в нашем приюте");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Информация о приюте");
        button1.callbackData("Информация о приюте");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Расписание работы");
        button2.callbackData("Расписание работы");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Рекомендации по ТБ");
        button3.callbackData("Рекомендации по ТБ");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);
        keyboard.addRow(saveInfo());
        keyboard.addRow(helpVolunteers());
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }

    public InlineKeyboardButton helpVolunteers() { // метод позвать волонтера
        InlineKeyboardButton button = new InlineKeyboardButton("Позвать волонтера");
        button.callbackData("позвать волонтера");
        return button;
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
            reportRepository.save(report);
            telegramBot.execute(new SendMessage(chatId, "Ваш отчет сохранен,следующий отчет отправьте завтра"));

        }
    }
}

