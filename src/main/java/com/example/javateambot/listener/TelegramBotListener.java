package com.example.javateambot.listener;

import com.example.javateambot.service.UserContactService;
import com.example.javateambot.service.TelegramBotService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@Service
//public class TelegramBotListener implements UpdatesListener {
//    private static final String NAME_OF_SHELTER = "The Best Shelter";
//    private static final Logger logger = LoggerFactory.getLogger(TelegramBotListener.class);
//
//    private static final Pattern pattern = Pattern.compile("([\\d\\\\.:\\s]{16})(\\s)([А-яA-z\\s\\d,.!?:]+)");
//
//    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
//
//    private final TelegramBot telegramBot;
//    private final TelegramBotService telegramBotService;
//
//    public TelegramBotListener(TelegramBot telegramBot, TelegramBotService telegramBotService) {
//        this.telegramBot = telegramBot;
//        this.telegramBotService = telegramBotService;
//    }
//
//    @PostConstruct
//    public void init() {
//        telegramBot.setUpdatesListener(this);
//    }
//
//    public int process(List<Update> updates) {
//        updates.forEach(update -> {
//            logger.info("Processing update: {}", update);
//            try {
//                Long chatId = update.message().chat().id();
//                String text = update.message().text();
//
//                if ("/start".equalsIgnoreCase(text)) {
//                    telegramBotService.sendWelcomeMessage(chatId);
//                } else if ("1".equalsIgnoreCase(text)) {
//                    telegramBotService.sendFirstTimeMessage(chatId);
//                    switch (text) {
//                        case "1" :
//                            telegramBotService.descriptionOfShelter(chatId);
//                            break;
//                        case "2" :
//
//                    }
//
//                }
//            } catch (TelegramApiException e) {
//                logger.error("При обновлении произошла ошибка", e);
//            }
//        });
//        return UpdatesListener.CONFIRMED_UPDATES_ALL;
//    }
//}

@Service
public class TelegramBotListener implements UpdatesListener {

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private TelegramBotService telegramBotService;


    private UserContactService userContactService;

    private final Logger logger = LoggerFactory.getLogger(TelegramBotListener.class);

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }
    private static final Pattern TELEPHONE_MESSAGE = Pattern.compile(
            "(\\d{11})(\\s)([А-яA-z)]+)(\\s)([А-яA-z)\\s\\d]+)"); // парсим сообщение на группы по круглым скобкам
    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                logger.info("Processing update: {}", update);

                if (update.callbackQuery() != null) {  // обработка этапа 0
                    Long chatId = update.callbackQuery().message().chat().id();
                    CallbackQuery callbackQuery = update.callbackQuery();
                    String data = callbackQuery.data();
                    switch (data) {

                        case "1" -> telegramBotService.shelterInfo(chatId);
                        case "инфа о приюте" ->
                                telegramBot.execute(new SendMessage(chatId, "тут должна быть информация о приюте."));
                        case "расписание работы" ->
                                telegramBot.execute(new SendMessage(chatId, " тут должно быть расписание работы приюта и адрес, схему проезда."));
                        case "рекомендации о ТБ" ->
                                telegramBot.execute(new SendMessage(chatId, "тут должны быть общие рекомендации о технике безопасности на территории приюта."));

                        case "2" -> telegramBotService.takeDogFromShelter(chatId);
                        case "Правила знакомства" ->
                                telegramBot.execute(new SendMessage(chatId, " тут должны быть правила знакомства с собакой до того, как можно забрать ее из приюта."));
                        case "Список документов" ->
                                telegramBot.execute(new SendMessage(chatId, " тут должен быть список документов, необходимых для того, чтобы взять собаку из приюта."));
                        case "транспортировка животного" ->
                                telegramBot.execute(new SendMessage(chatId, " тут должно быть список рекомендаций по транспортировке животного."));
                        case "дома для щенка" ->
                                telegramBot.execute(new SendMessage(chatId, " тут должно быть список рекомендаций по обустройству дома для щенка."));
                        case "дома для собаки" ->
                                telegramBot.execute(new SendMessage(chatId, " тут должно быть список рекомендаций по обустройству дома для взрослой собаки."));
                        case "дома для собаки с ограничениями" ->
                                telegramBot.execute(new SendMessage(chatId, " тут должен быть список рекомендаций по обустройству дома для собаки с ограниченными возможностями (зрение, передвижение)."));
                        case "советы кинолога" ->
                                telegramBot.execute(new SendMessage(chatId, " тут должны быть советы кинолога по первичному общению с собакой"));
                        case "список кинологов" ->
                                telegramBot.execute(new SendMessage(chatId, " тут должны быть рекомендации по проверенным кинологам для дальнейшего обращения к ним."));
                        case "список причин для отказа" ->
                                telegramBot.execute(new SendMessage(chatId, " тут должен быть список причин, почему могут отказать и не дать забрать собаку из приюта."));

                        case "3" -> telegramBotService.sendReport(chatId);
                        case "Форма ежедневного отчёта" -> {
                            telegramBot.execute(new SendMessage(chatId, "Форма ежедневного отчёта:\n1. Фото животного\n2. Рацион животного\n" +
                                    "3. Общее самочувствие и привыкание к новому месту \n4. Изменение в поведении"));
                            telegramBotService.sendReport(chatId);
                        }
                        case "принимаем отчет" ->
                                telegramBot.execute(new SendMessage(chatId, "Вышлите фото животного"));

                        case "позвать волонтера" -> telegramBot.execute(new SendMessage(chatId, "Зовем волонтера"));
                        case "записать данные" -> {
                            telegramBot.execute(new SendMessage(chatId, "Введите номер телефона и вопрос в формате: 89001122333 Ваш вопрос."));
                            Matcher matcher = TELEPHONE_MESSAGE.matcher(data);
                            if (matcher.find()) {  //find запускает matcher
                                try {
                                    Integer telephone = Integer.valueOf(matcher.group(1)); // получаем телефон
                                    String name = matcher.group(3); // получаем имя
                                    String messageText = matcher.group(5); // получаем текст сообщения
                                    userContactService.addUserContact(chatId,name,telephone/*messageText */); // создаем и пишем контакт в базу
                                    SendMessage message = new SendMessage(chatId, "Данные записаны, В ближайшее время мы с Вами свяжемся");
                                    telegramBot.execute(message);
                                } catch (DateTimeParseException e) {
                                    SendMessage messageEx = new SendMessage(chatId, "Некорректный формат номера телефона или сообщения");
                                    telegramBot.execute(messageEx);
                                }
                            }
                        }

                    }
                    return;
                }


                User user = update.message().from();
                Long chatId = user.id();

                if (update.message().text().equals("/start")) {  // этап 0
                    telegramBotService.firstMenu(chatId);
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}














/*
Пользователь обращается, бот выводит сообщение:
Добрый день!
Добрый день! Приветствуем Вас в нашем приюте для животных NAME_OF_SHELTER.
Подскажите пожалуйста, Вы:
1. впервые в нашем приюте
2. ранее обращались к нам
3. отправить отчет о ранее взятом животном


Если впервые, то человеку предлагается выбрать
"Что Вам подсказать:" +
                "1. Рассказать о приюте\n" +
                "2. Выдать расписание работы приюта и адрес, схему проезда" +
                "3. Выдать общие рекомендации о технике безопасности на территории приюта\n" +
                "4. Оставить контактные данные, чтобы мы связались с Вами\n" +
                "5. Позвать оператора";


Если повторно, то человеку предлагается выбрать
"Что Вам подсказать:\n" +
                "1. Правила знакомства с собакой до того, как можно забрать ее из приюта\n" +
                "2. Список документов, необходимый, чтобы забрать питомца\n" +
                "3. Список рекомендаций по транспортировке животного\n" +
                "4. Список рекомендаций по обустройству дома для щенка\n" +
                "5. список рекомендаций по обустройству дома для взрослой собаки" +
                "6. Список рекомендаций по обустройству дома для собаки с ограниченными возможностями\n" +
                "7. Советы кинолога по первичному общению с собакой\n" +
                "8. Рекомендации по проверенным кинологам для дальнейшего обращения к ним\n" +
                "9. Список причин, почему могут отказать и не дать забрать собаку из приюта\n" +
                "10. Изменить контактные данные\n" +
                "11. Позвать оператора";


Если отправить отчет, то человеку нужно прислать
    - Картинку (фото животного)
    - Текст (рацион животного, общее самочувствие и привыкание к новому месту, изменение в поведении).
Если человек отправил, что-то одно - нужно запросить второе
Если отчет не приходил более 2х дней, необходимо напомнинать волонтеру об этом об этом.
Если усыновитель прошел испытательный срок, то бот поздравляет его стандартным сообщением.
Если усыновителю было назначено дополнительное время испытательного срока, то бот сообщает ему и указывает количество дополнительных дней.
Если усыновитель не прошел испытательный срок, то бот уведомляет его об этом и дает инструкции по дальнейшим шагам.
Если бот не может ответить на вопросы клиента, то можно позвать волонтера.



 */


//    public void process() {
//        telegramBot.setUpdatesListener(updates -> {
//            for (var update : updates) {
//                try {
//                    if (update.message() != null && update.message().text() != null) {
//                        Long chatId = update.message().chat().id();
//                        String message = update.message().text();
//                        switch (message) {
//                            case "/start":
//                                sendInitialMessage(chatId);
//                                break;
//                            case "1":
//                                sendFirstTimeMessage(chatId);
//                                break;
//                            case "2":
//                                sendRepeatMessage(chatId);
//                                break;
//                            case "3":
//                                sendReportReminder(chatId);
//                                break;
//                            case "4":
//                                sendContactInformationRequest(chatId);
//                                break;
//                            case "5":
//                                inviteVolunteer(chatId);
//                                break;
//                            case "6":
//                                sendDogIntroductionRules(chatId);
//                                break;
//                            case "7":
//                                sendDocumentsRequired(chatId);
//                                break;
//                            case "8":
//                                sendTransportationRecommendations(chatId);
//                                break;
//                            case "9":
//                                sendPuppyHomeRecommendations(chatId);
//                                break;
//                            case "10":
//                                sendAdultDogHomeRecommendations(chatId);
//                                break;
//                            case "11":
//                                sendSpecialNeedsDogHomeRecommendations(chatId);
//                                break;
//                            case "12":
//                                sendK9ExpertAdvice(chatId);
//                                break;
//                            case "13":
//                                sendK9ExpertRecommendations(chatId);
//                                break;
//                            case "14":
//                                sendAdoptionRefusalReasons(chatId);
//                                break;
//                            default:
//                                sendVolunteerContactInformation(chatId);
//                                break;
//                        }
//                    }
//                } catch (TelegramApiException e) {
//                    logger.error("Error while processing update", e);
//                }
//            }
//            return UpdatesListener.CONFIRMED_UPDATES_ALL;
//        });
//    }
//    private void sendInitialMessage(Long chatId) throws TelegramApiException {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Добрый день! Приветствуем Вас в нашем приюте для животных " + NAME_OF_SCHELTER + ".\n"
//                + "Подскажите пожалуйста, Вы:\n"
//                + "1. Впервые в нашем приюте\n"
//                + "2. Ранее обращались к нам\n"
//                + "3. Отправить отчет о ранее взятом животном");
//        telegramBot.execute(message);
//    }
//
//    private void sendFirstTimeMessage(Long chatId) throws TelegramApiException {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Что Вам подсказать:\n"
//                + "1. Рассказать о приюте\n"
//                + "2. Узнать, как можно помочь\n"
//                + "3. Посмотреть фотографии животных, ожидающих своего хозяина\n"
//                + "4. Задать вопрос");
//        telegramBot.execute(message);
//    }
//
//    private void sendRepeatMessage(Long chatId) throws TelegramApiException {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Что еще Вы хотели узнать?\n"
//                + "1. Рассказать о приюте\n"
//                + "2. Узнать, как можно помочь\n"
//                + "3. Посмотреть фотографии животных, ожидающих своего хозяина\n"
//                + "4. Задать вопрос");
//        telegramBot.execute(message);
//    }
//
//    private void sendReportReminder(Long chatId) throws TelegramApiException {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Для отправки отчета, пожалуйста, введите информацию о животном в формате:\n\n"
//                + "ДАТА КАК СТРОКА ИМЯ_ЖИВОТНОГО ВОЗРАСТ_ЖИВОТНОГО ПОЛ_ЖИВОТНОГО ОПИСАНИЕ");
//        telegramBot.execute(message);
//    }
//
//    private void sendContactInformationRequest(Long chatId) throws TelegramApiException {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Пожалуйста, напишите Ваше имя, фамилию и номер телефона. Мы свяжемся с Вами в ближайшее время.");
//        telegramBot.execute(message);
//    }
//
//    private void inviteVolunteer(Long chatId) throws TelegramApiException {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Благодарим Вас за желание помочь! Оставьте, пожалуйста, свои контактные данные (имя, фамилию, номер телефона), чтобы мы могли связаться с Вами.");
//        telegramBot.execute(message);
//    }
//
//    private void sendDogIntroductionRules(Long chatId) throws TelegramApiException {
//        SendMessage message = new SendMessage();
//        message.setChatId(chatId);
//        message.setText("Правила знакомства с собаками:\n"
//                        + "1. Подходите к собаке спокойно, не грубо и не стремительно\n"
//                        + "2. Говорите с собакой тихим голосом\n"
//                        + "3. Не кормите собаку без разрешения персонала приюта\n"
//                        + "4. Не приближайтесь к собаке, которая спит или употребляет пищу\n"
//                        + "5. Не бегайте и не прыгайте вокруг собаки\n"
//                        + "6. Приятный звук, который может привлечь внимание собаки - это звук целебного щелчка\n"
//                        + "7. Если собака начинает вести себя странно, не пытайтесь обойти ее, оставьте ее в покое\n"
//                        + "8. Если собака начинает лаять, прежде чем вы попытаетесь ее успокоить, подождите, пока она успокоится сама\n"
//                        + "9. Никогда не дразните и не бросайте предметы в собаку\n"
//                        + "10. Позвольте собаке подойти к вам и познакомиться, если она этого хочет\n"
//                        + "11. Не допускайте детей, чтобы они подходили к собакам без разрешения взрослых\n"
//                        + "12. Не оставляйте детей без присмотра возле собак\n"
//                        + "13. Если у вас есть собственный питомец, не позволяйте ему входить в зону, где находятся приютские собаки\n"
//                        + "14. Старайтесь не совершать резких движений, таких как быстрый подход или отход от собаки");
//        telegramBot.execute(message);
//    }

