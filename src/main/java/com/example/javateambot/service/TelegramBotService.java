package com.example.javateambot.service;

import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.repository.ContactInformationRepository;
import com.example.javateambot.repository.ReportRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotService {

    Logger logger = LoggerFactory.getLogger(TelegramBotService.class);

    private TelegramBot telegramBot;

    private ContactInformationRepository contactInformationRepository;


    private ReportRepository reportRepository;


    private PhotoService photoService;

    public TelegramBotService(TelegramBot telegramBot, ContactInformationRepository contactInformationRepository, ReportRepository reportRepository, PhotoService photoService) {
        this.telegramBot = telegramBot;
        this.contactInformationRepository = contactInformationRepository;
        this.reportRepository = reportRepository;
        this.photoService = photoService;
    }

    public InlineKeyboardButton saveInfo() {  // метод записи данных
        InlineKeyboardButton button = new InlineKeyboardButton("записать данные");
        button.callbackData("записать данные");
        return button;
    }

    public void takeDogFromShelter(Long chatId) {  // кнопки этапа 2, кейсы между 2 и 3
        SendMessage message = new SendMessage(chatId, "Рекомендации для собак");

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
//        keyboard.addRow(saveInfo());
//        keyboard.addRow(helpVolunteers());
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }
        public void takeCatFromShelter1(Long chatId1) {  // кнопки этапа 2.1, кейсы между 2 и 3
            SendMessage message1 = new SendMessage(chatId1, "Рекомендации для кошек");

            InlineKeyboardButton rulesForCat = new InlineKeyboardButton("Правила знакомства с кошкой до того, как можно забрать ее из приюта.");
            rulesForCat.callbackData("Правила знакомства c котом");
            InlineKeyboardButton documents = new InlineKeyboardButton("Список документов, необходимых для того, чтобы взять кошку из приюта.");
            documents.callbackData("Список документов для кота");
            InlineKeyboardButton b3 = new InlineKeyboardButton("Список рекомендаций по транспортировке кошки.");
            b3.callbackData("Транспортировка животного для кота");
            InlineKeyboardButton b4 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для котенка.");
            b4.callbackData("дома для кота");
            InlineKeyboardButton b5 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для взрослой кошки-кота.");
            b5.callbackData("дома для взрослого кота");
            InlineKeyboardButton b6 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для кошки с ограниченными возможностями (зрение, передвижение).");
            b6.callbackData("дома для кота с ограничениями");
            InlineKeyboardButton b7 = new InlineKeyboardButton("Советы кинолога по первичному общению с кошки");
            b7.callbackData("советы кинолога для кота");
            InlineKeyboardButton b8 = new InlineKeyboardButton("Рекомендации по проверенным кинологам для дальнейшего обращения .");
            b8.callbackData("список кинологов для кота");
            InlineKeyboardButton b9 = new InlineKeyboardButton("Список причин, почему могут отказать и не дать забрать кошку из приюта.");
            b9.callbackData("список причин для отказа для кота");
            InlineKeyboardMarkup keyboard1 = new InlineKeyboardMarkup();
            keyboard1.addRow(rulesForCat);
            keyboard1.addRow(documents);
            keyboard1.addRow(b3);
            keyboard1.addRow(b4);
            keyboard1.addRow(b5);
            keyboard1.addRow(b6);
            keyboard1.addRow(b7);
            keyboard1.addRow(b8);
            keyboard1.addRow(b9);
            message1.replyMarkup(keyboard1);
            telegramBot.execute(message1);
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
        SendMessage helloMessage = new SendMessage(chatId, "Привет, тут должна быть информация о боте. Выберите интересующий пункт из меню: ");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Узнать информацию о приюте!");
        button1.callbackData("1");
        InlineKeyboardButton button2 = new InlineKeyboardButton("Как взять собаку из приюта?");
        button2.callbackData("2");
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце");
        button3.callbackData("3");
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера");
        button4.callbackData("позвать волонтера");
        InlineKeyboardButton button5 = new InlineKeyboardButton("Как взять кошку из приюта?");
        button5.callbackData("4");
        InlineKeyboardButton button6 = new InlineKeyboardButton("Записать данные");
        button6.callbackData("6");
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1,button3);
        keyboard.addRow(button2,button4);
        keyboard.addRow(button5,saveInfo());
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
//        keyboard.addRow(saveInfo());
//        keyboard.addRow(helpVolunteers());
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }

    /**
     * Метод показывает пользователю кнопу "Позвать волонтера" в чате бота, использует {@link InlineKeyboardButton}
     *
     * @return возвращает созданную кнопку
     */

    public InlineKeyboardButton helpVolunteers() { // метод позвать волонтера
        InlineKeyboardButton button = new InlineKeyboardButton("Позвать волонтера");
        button.callbackData("позвать волонтера");
        return button;
    }


    /**
     * Метод показывает пользователю кнопу "Главное меню" для возврата в основное меню в чате бота,
     * использует {@link InlineKeyboardButton}
     *
     * @return возвращает созданную кнопку
     */
    public InlineKeyboardButton mainMenu() { // // возврат в главное меню
        InlineKeyboardButton button = new InlineKeyboardButton("Главное меню");
        button.callbackData("Главное меню");
        return button;
    }

//    public void saveContactData(String message, Long chatId) {
//
//        ContactInformation contactInformation = new ContactInformation();
//        String messageText = message;
//        String[] fields = messageText.split(" ");
//        String[] fields1 = messageText.split("\n");
//
//        if (fields.length == 3) {
//
//            String firstName = fields[0].trim();
//            contactInformation.setFirstname(firstName);
//            String lastName = fields[1].trim();
//            contactInformation.setLastName(lastName);
//            String phoneNumber = fields[2].trim();
//            contactInformation.setPhoneNumber(phoneNumber);
//            contactInformation.setChatId(chatId);
//            contactInformationRepository.save(contactInformation);
//            telegramBot.execute(new SendMessage(chatId, "Ваши данные сохранены"));
//
//        } else if (fields1.length == 3) {
//
//            String firstName = fields1[0].trim();
//            contactInformation.setFirstname(firstName);
//            String lastName = fields1[1].trim();
//            contactInformation.setLastName(lastName);
//            String phoneNumber = fields1[2].trim();
//            contactInformation.setPhoneNumber(phoneNumber);
//            contactInformation.setChatId(chatId);
//            contactInformationRepository.save(contactInformation);
//            telegramBot.execute(new SendMessage(chatId, "Ваши данные сохранены"));
//
//        }
//    }



}

