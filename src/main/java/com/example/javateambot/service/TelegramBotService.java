package com.example.javateambot.service;

import com.example.javateambot.repository.ContactInformationRepository;
import com.example.javateambot.repository.ReportRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.javateambot.listener.TelegramBotListener.*;

@Service
public class TelegramBotService {


    private final  Logger logger = LoggerFactory.getLogger(TelegramBotService.class);



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
        button.callbackData(WRITE_DOWN_CONTACT_DATA);
        return button;
    }

    public void takeDogFromShelter(Long chatId) {  // кнопки этапа 2, кейсы между 2 и 3
        SendMessage message = new SendMessage(chatId, "Рекомендации для собак");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Правила знакомства с собакой до того, как можно забрать ее из приюта.");
        button1.callbackData(RULES_FOR_DATING);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Список документов, необходимых для того, чтобы взять собаку из приюта.");
        button2.callbackData(LIST_OF_DOCUMENTS);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Список рекомендаций по транспортировке животного.");
        button3.callbackData(RECOMMENDATIONS_FOR_TRANSPORTATION);
        InlineKeyboardButton button4 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для щенка.");
        button4.callbackData(HOME_IMPROVEMENT_FOR_PUPPY);
        InlineKeyboardButton button5 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для взрослой собаки.");
        button5.callbackData(HOME_IMPROVEMENT_FOR_ADULT_DOG);
        InlineKeyboardButton button6 = new InlineKeyboardButton("Список рекомендаций по обустройству дома для собаки с ограниченными возможностями (зрение, передвижение).");
        button6.callbackData(HOME_IMPROVEMENT_FOR_DOG_WITH_DISABILITIES);
        InlineKeyboardButton button7 = new InlineKeyboardButton("Советы кинолога по первичному общению с собакой");
        button7.callbackData(TIPS_FROM_DOG_HANDLER);
        InlineKeyboardButton button8 = new InlineKeyboardButton("Рекомендации по проверенным кинологам для дальнейшего обращения к ним.");
        button8.callbackData(INFO_ABOUT_DOG_HANDLER);
        InlineKeyboardButton button9 = new InlineKeyboardButton("Список причин, почему могут отказать и не дать забрать собаку из приюта.");
        button9.callbackData(REASONS_FOR_REFUSAL);
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
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }

    public void sendReport(Long chatId) {  // кнопки этапа 3, кейсы между 2 и 3
        SendMessage message = new SendMessage(chatId, "Приветствует в нашем приюте");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Форма ежедневного отчёта");
        button1.callbackData(FORM_DAILY_REPORT);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Прислать ежедневный отчёт");
        button2.callbackData(RECIEVE_REPORT);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(helpVolunteers());
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }

    public void firstMenu(Long chatId) { // меню начальное, кейсы 1/2/3
        SendMessage helloMessage = new SendMessage(chatId, "Главное меню. Выберите интересующий пункт из меню: ");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Узнать информацию о приюте!");
        button1.callbackData(SHELTER_INFO_MENU);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Как взять собаку из приюта?");
        button2.callbackData(HOW_TO_TAKE_DOG);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Прислать отчет о питомце");
        button3.callbackData(SEND_REPORT);
        InlineKeyboardButton button4 = new InlineKeyboardButton("Позвать волонтера");
        button4.callbackData(CALL_VOLUNTEER);
        InlineKeyboardButton button5 = new InlineKeyboardButton("Как взять кошку из приюта?");
        button5.callbackData(HOW_TO_TAKE_CAT);
        InlineKeyboardButton button6 = new InlineKeyboardButton("Записать данные");
        button6.callbackData(WRITE_DOWN_CONTACT_DATA);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1,button3);
        keyboard.addRow(button2,button4);
        keyboard.addRow(cats(),saveInfo());
        helloMessage.replyMarkup(keyboard);
        telegramBot.execute(helloMessage);
    }

    public void shelterInfo(Long chatId) { // кнопки этапа 1, кейсы между 1 и 2

        SendMessage message = new SendMessage(chatId, "Приветствует в нашем приюте");

        InlineKeyboardButton button1 = new InlineKeyboardButton("Информация о приюте");
        button1.callbackData(INFO_ABOUT_SHELTER);
        InlineKeyboardButton button2 = new InlineKeyboardButton("Расписание работы");
        button2.callbackData(WORK_SCHEDULE);
        InlineKeyboardButton button3 = new InlineKeyboardButton("Рекомендации по ТБ");
        button3.callbackData(SAFETY_RECOMMENDATIONS);
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button1);
        keyboard.addRow(button2);
        keyboard.addRow(button3);
        message.replyMarkup(keyboard);
        telegramBot.execute(message);
    }
    public void catInfo(Long chatId) { // кнопки этапа 1, кейсы между 1 и 2

        SendMessage message = new SendMessage(chatId, "Рекомендации для кошек");

        InlineKeyboardButton button12 = new InlineKeyboardButton("Правила знакомства с кошкой до того, как можно забрать ее из приюта.");
        button12.callbackData(RULES_FOR_DATING_For_Cat);
        InlineKeyboardButton documents = new InlineKeyboardButton("Список документов, необходимых для того, чтобы взять кошку из приюта.");
        documents.callbackData(LIST_OF_DOCUMENTS1);
        InlineKeyboardButton b4 = new InlineKeyboardButton("Список рекомендаций по транспортировке кошки.");
        b4.callbackData(RECOMMENDATIONS_FOR_TRANSPORTATION1);
        InlineKeyboardButton kittyHome = new InlineKeyboardButton("Список рекомендаций по обустройству дома для котенка.");
        kittyHome.callbackData(HOME_IMPROVEMENT_FOR_PUPPY1);
        InlineKeyboardButton adultCat = new InlineKeyboardButton("Список рекомендаций по обустройству дома для взрослой кота.");
        adultCat.callbackData(HOME_IMPROVEMENT_FOR_ADULT_CAT);
        InlineKeyboardButton disability = new InlineKeyboardButton("Список рекомендаций по обустройству дома для кошки с ограниченными возможностями (зрение, передвижение).");
        disability.callbackData(HOME_IMPROVEMENT_FOR_CAT_WITH_DISABILITIES1);
        InlineKeyboardButton recomend = new InlineKeyboardButton("Советы кинолога по первичному общению с кошки");
        recomend.callbackData(TIPS_FROM_CAT_HANDLER1);
        InlineKeyboardButton recomendPeople = new InlineKeyboardButton("Рекомендации по проверенным кинологам для дальнейшего обращения .");
        recomendPeople.callbackData(INFO_ABOUT_CAT_HANDLER1);
        InlineKeyboardButton cancel = new InlineKeyboardButton("Список причин, почему могут отказать и не дать забрать кошку из приюта.");
        cancel.callbackData(REASONS_FOR_REFUSAL1);

        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        keyboard.addRow(button12);
        keyboard.addRow(documents);
        keyboard.addRow(b4);
        keyboard.addRow(kittyHome);
        keyboard.addRow(adultCat);
        keyboard.addRow(disability);
        keyboard.addRow(recomend);
        keyboard.addRow(recomendPeople);
        keyboard.addRow(cancel);
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
        button.callbackData(CALL_VOLUNTEER);
        return button;
    }
    public InlineKeyboardButton cats() { // метод кнопка кошка
        InlineKeyboardButton button = new InlineKeyboardButton("Как взять кошку из приюта, советы");
        button.callbackData(HOW_TAKE_CAT);
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

