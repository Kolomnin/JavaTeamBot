package com.example.javateambot.listener;


import com.example.javateambot.repository.*;
import com.example.javateambot.service.*;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
//import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class TelegramBotListener implements UpdatesListener {

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private TelegramBotService telegramBotService;

    @Autowired
    private DogAdoptionRepository dogAdoptionRepository;

    @Autowired
    private ContactInformationRepository contactInformationRepository;

    @Autowired
    private ReportRepository reportRepository;


    @Autowired
    private SaveReportAndContactData saveReportAndContactData;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private DogAdoptionService dogAdoptionService;

    public static final String INFO_ABOUT_SHELTER = "Информация о приюте";
    public static final String WORK_SCHEDULE = "Расписание работы";
    public static final String SAFETY_RECOMMENDATIONS = "Рекомендации по ТБ";
    public static final String RULES_FOR_DATING = "Правила знакомства";
    public static final String LIST_OF_DOCUMENTS = "Список документов";
    public static final String RECOMMENDATIONS_FOR_TRANSPORTATION = "транспортировка животного";
    public static final String HOME_IMPROVEMENT_FOR_PUPPY = "дома для щенка";
    public static final String HOME_IMPROVEMENT_FOR_ADULT_DOG = "дома для собаки";
    public static final String HOME_IMPROVEMENT_FOR_DOG_WITH_DISABILITIES = "дома для собаки с ограничениями";
    public static final String TIPS_FROM_DOG_HANDLER = "советы кинолога";
    public static final String INFO_ABOUT_DOG_HANDLER = "список кинологов";
    public static final String REASONS_FOR_REFUSAL = "список причин для отказа";

    private final TelegramDogService telegramDogService;

    private final Logger logger = LoggerFactory.getLogger(TelegramBotListener.class);

    @Autowired
    public TelegramBotListener(TelegramBot telegramBot, TelegramBotService telegramBotService,
                               TelegramDogService telegramDogService, PhotoService photoService) {
        this.telegramBot = telegramBot;
        this.telegramBotService = telegramBotService;
        this.telegramDogService = telegramDogService;
        this.photoService = photoService;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

//    private static final Pattern TELEPHONE_MESSAGE = Pattern.compile(
//            "(\\d{11})(\\s)([А-яA-z)]+)(\\s)([А-яA-z)\\s\\d]+)"); // парсим сообщение на группы по круглым скобкам

    Long chatId;

    @Override
    public int process(List<Update> updates) {
        try {
            updates.forEach(update -> {
                        logger.info("Processing update: {}", update);
                        //тут две кнопки приют кошки или собаки если

                        if (update.callbackQuery() != null) {  // обработка этапа 0
                            chatId = update.callbackQuery().message().chat().id();
                            CallbackQuery callbackQuery = update.callbackQuery();
                            String data = callbackQuery.data();
                            try {
                                System.out.println(update.message().text());

                            } catch (Exception e) {
                                System.out.println("Ошибка");
                            }

                            switch (data) {

                                case "1" -> telegramBotService.shelterInfo(chatId);
                                case INFO_ABOUT_SHELTER ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.descriptionOfShelter()));
                                case WORK_SCHEDULE ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.infoAboutShelter()));
                                case SAFETY_RECOMMENDATIONS ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.safetyRecommendations()));

                                case "2" -> telegramBotService.takeDogFromShelter(chatId);
                                case "2.1" -> telegramBotService.takeCatFromShelter(chatId);
                                case RULES_FOR_DATING ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.rulesForDating()));
                                case LIST_OF_DOCUMENTS ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.listOfDocuments()));
                                case RECOMMENDATIONS_FOR_TRANSPORTATION ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.recommendationsForTransportation()));
                                case HOME_IMPROVEMENT_FOR_PUPPY ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.homeImprovementForPuppy()));
                                case HOME_IMPROVEMENT_FOR_ADULT_DOG ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.homeImprovementForAdultDog()));
                                case HOME_IMPROVEMENT_FOR_DOG_WITH_DISABILITIES ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.homeImprovementForDogWithDisabilities()));
                                case TIPS_FROM_DOG_HANDLER ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.TipsFromDogHandler()));
                                case INFO_ABOUT_DOG_HANDLER ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.InfoAboutDogHandler()));
                                case REASONS_FOR_REFUSAL ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.ReasonsForRefusal()));

                                case "3" -> telegramBotService.sendReport(chatId);
                                case "Форма ежедневного отчёта" -> {
                                    telegramBot.execute(new SendMessage(chatId, "Форма ежедневного отчёта:" +
                                            "\n1. Фото животного" +
                                            "\n2. Рацион животного" +
                                            "\n3. Общее самочувствие и привыкание к новому месту " +
                                            "\n4. Изменение в поведении"));
                                    telegramBotService.sendReport(chatId);
                                }
                                case "аудентификация" ->
                                        telegramBot.execute(new SendMessage(chatId, "Введите номер телефона для подтверждения личности"));

                                case "принимаем отчет" ->
                                        telegramBot.execute(new SendMessage(chatId, "Вышлите фото животного"));

                                case "позвать волонтера" -> telegramBot.execute(new SendMessage(chatId, "Зовем волонтера"));

                                case "записать данные" -> {
                                    telegramBot.execute(new SendMessage(chatId, "Необходимо ввести три поля: имя, фамилию и номер телефона. В формате:\n" +
                                            "Иван\n" +
                                            "Петров\n" +
                                            "79290463013\n" +
                                            "Или Напишите данные в одну строчку через один пробел"));

                                }
                            }
                        }
                        chatId = update.message().chat().id();

                        if ("/start".equals(update.message().text())) {  // этап 0
                            telegramBotService.firstMenu(chatId);

                        }

                        /**
                         * Проверяем сообщение пользователя на соответствие и сохраняем в БД,
                         * или выдаем информацию о несоответствии шаблону для сохранения.
                         */
//                        else if (update.message().text() != null) {
//
//                            Matcher matcher = TELEPHONE_MESSAGE.matcher(update.message().text());
//                            if (matcher.find()) {  //find запускает matche
//                                telegramBot.execute(new SendMessage(chatId, "успешно"));
//
//                            }
//                        } else if (checkUrl(update.message().text())) {
//                            telegramBot.execute(new SendMessage(chatId, "Вы найдены, теперь отправьте фото"));
//
//                        }
                        /**
                         * Тут обрабатывается прием фото для отчета если в update есть фото и chatId есть в нашей базе
                         * усыновителей тогда принимаем от него фотографию и создаем обьект отчета, присваиваем ему фото
                         * затем сохраняем в бд
                         */

                        if (update.message().photo() != null && dogAdoptionService.checkChatId(chatId)) { //
//
                            photoService.uploadPhoto(update.message());
                            telegramBot.execute(new SendMessage(chatId, "Теперь напишите нам о рационе, состоянии поведения питомца," +
                                    "общем самочувствии, привыканию к новому месту, новые обретенные привычки.\n" +
                                    "В данном формате\n" +
                                    "1. Рацион\n" +
                                    "2. Поведение\n" +
                                    "3. Общее самочувствие и привыкание к новому месту\n" +
                                    "4. Новые привычки\n"));

                            telegramBot.execute(new SendMessage(chatId, "Пример:\n" +
                                    "Утром- корм, обед-макароны,полдник-жидкий корм,ужин-косточка, перед сном-молоко\n" +
                                    "Бобик находится в состоянии адаптации\n" +
                                    "Самочувствие игривое, потихоньку изучает квартиру, боится далеко отходить от конуры\n" +
                                    "Новых привычек не обнаружено\n"));
                            telegramBot.execute(new SendMessage(chatId, "После заполнения каждого критерия ставьте точку если пишите в одноу строчку или с начинайте с новой строки как в образце"));

                        }

                        /**
                         * Тут обрабатвается команда записать данные, когда любой пользователь оставляет контактные данные в боте.
                         */

                        saveReportAndContactData.saveContactData(update.message().text(), chatId);

                        /**
                         * Это обработчик отчетов, когда нам владелец присылает отчет.
                         */
                        saveReportAndContactData.saveReport(update.message().text(), chatId);

                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

//    public static boolean checkUrl(String s) {
//        String regex = "^\\+?[0-9\\-\\s]*$";
//        return s != null && s.matches(regex);
//    }

}






