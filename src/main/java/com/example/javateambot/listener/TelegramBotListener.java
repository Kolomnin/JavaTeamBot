package com.example.javateambot.listener;


import com.example.javateambot.entity.ContactInformation;
import com.example.javateambot.entity.Report;
import com.example.javateambot.repository.*;
import com.example.javateambot.service.*;


import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
//import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class TelegramBotListener implements UpdatesListener {


    @Autowired
    private TelegramBot telegramBot;
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private TelegramBotService telegramBotService;
    @Autowired
    AnimalsInHouseRepository animalsInHouseRepository;

    @Autowired
    ContactInformationRepository contactInformationRepository;


    @Autowired
    ReportRepository reportRepository;








    private UsersContactService userContactService;

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


    private final TelegramService telegramService;


    private final Logger logger = LoggerFactory.getLogger(TelegramBotListener.class);

    @Autowired
    public TelegramBotListener(TelegramBot telegramBot, TelegramBotService telegramBotService,
                               TelegramService telegramService, PhotoService photoService) {
        this.telegramBot = telegramBot;
        this.telegramBotService = telegramBotService;
        this.telegramService = telegramService;
        this.photoService = photoService;
    }

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
                            try {
                                System.out.println(update.message().text());

                            } catch (Exception e) {
                                System.out.println("Ошибка");
                            }


                            switch (data) {

                                case "1" -> telegramBotService.shelterInfo(chatId);
                                case INFO_ABOUT_SHELTER ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.descriptionOfShelter()));
                                case WORK_SCHEDULE ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.infoAboutShelter()));
                                case SAFETY_RECOMMENDATIONS ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.safetyRecommendations()));

                                case "2" -> telegramBotService.takeDogFromShelter(chatId);
                                case RULES_FOR_DATING ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.rulesForDating()));
                                case LIST_OF_DOCUMENTS ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.listOfDocuments()));
                                case RECOMMENDATIONS_FOR_TRANSPORTATION ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.recommendationsForTransportation()));
                                case HOME_IMPROVEMENT_FOR_PUPPY ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.homeImprovementForPuppy()));
                                case HOME_IMPROVEMENT_FOR_ADULT_DOG ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.homeImprovementForAdultDog()));
                                case HOME_IMPROVEMENT_FOR_DOG_WITH_DISABILITIES ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.homeImprovementForDogWithDisabilities()));
                                case TIPS_FROM_DOG_HANDLER ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.TipsFromDogHandler()));
                                case INFO_ABOUT_DOG_HANDLER ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.InfoAboutDogHandler()));
                                case REASONS_FOR_REFUSAL ->
                                        telegramBot.execute(new SendMessage(chatId, telegramService.ReasonsForRefusal()));

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


//                            Matcher matcher = TELEPHONE_MESSAGE.matcher(data);
//                            if (matcher.find()) {  //find запускает matcher
//                                try {
//                                    Integer telephone = Integer.valueOf(matcher.group(1)); // получаем телефон
//                                    String name = matcher.group(3); // получаем имя
//                                    String messageText = matcher.group(5); // получаем текст сообщения
//                                    userContactService.addUserContact(chatId, name, telephone/*messageText */); // создаем и пишем контакт в базу
//                                    SendMessage message = new SendMessage(chatId, "Данные записаны, В ближайшее время мы с Вами свяжемся");
//                                    telegramBot.execute(message);
//                                } catch (DateTimeParseException e) {
//                                    SendMessage messageEx = new SendMessage(chatId, "Некорректный формат номера телефона или сообщения");
//                                    telegramBot.execute(messageEx);
//                                }
//                            }
                                }

                            }
//                    return;
                        }


                        User user = update.message().from();
                        Long chatId = user.id();

//                    * Обработка сообщения от пользователя и вызов основного меню
//                 */
                        if ("/start".equals(update.message().text())) {  // этап 0
                            telegramBotService.firstMenu(chatId);


                        }
//                /
//
                        /**
                         * Проверяем сообщение пользователя на соответствие и сохраняем в БД,
                         * или выдаем информацию о несоответствии шаблону для сохранения.
                         */
                        else if (update.message().text() != null) {

                            Matcher matcher = TELEPHONE_MESSAGE.matcher(update.message().text());
                            if (matcher.find()) {  //find запускает matche
                                telegramBot.execute(new SendMessage(chatId, "успешно"));

//                        adoptiveParentService.saveInfoDataBase(matcher, chatId);
                            }
                        } else if (checkUrl(update.message().text())) {
                            telegramBot.execute(new SendMessage(chatId, "Вы найдены, теперь отправьте фото"));

                        }
                        /**
                         * Тут обрабатывается прием фото для отчета если в update есть фото и chatId есть в нашей базе
                         * усыновителей тогда принимаем от него фотографию и создаем обьект отчета, присваиваем ему фото
                         * затем сохраняем в бд
                         */

                        Report report = new Report();
                        if (update.message().photo() != null) { //
                            //&& dogAdoptionService.checkChatId(chatId) вставить в if
                            // дописать проверку на наличие в базе данных усновителя этого чат id
                            //String report = update.message().caption(); тут находится описание отчета от владельца,когда отправляешь фото в описании фотографии
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


//                        dogAdoptionService.editAnimalInShelter(animalsInHouseRepository.findByIdUser(Long.parseLong("222"))
//                        ,report);

                            //dogAdoptionService.saveReport(report, animalsInHouseRepository.findByIdUser(Long.parseLong("1")), "79290463013");
//                            telegramBot.execute(new SendMessage(chatId, "Ваш отчет сохранен"));

                        }
                        /**
                         * Тут обрабатвается команда записать данные, когда любой пользователь оставляет контактные данные в боте.
                         */


                        telegramBotService.saveContactData(update.message().text(),chatId);


                        /**
                         * Это обработчик отчетов, когда нам владелец присылает отчет.
                         */

                        telegramBotService.saveReport(update.message().text(),chatId);

//                        String reportText = update.message().text();
//                        String[] fieldsForReport = reportText.split("\\.");
//                        System.out.println(fieldsForReport.length);
//                        String[] fieldsForReport1 = reportText.split("\n");
//                        if (fieldsForReport.length == 4) {
//
//
//                            String ration = fieldsForReport[0].trim();
//                            report.setRation(ration);
//                            String animalBehavior = fieldsForReport[1].trim();
//                            report.setAnimalBehavior(animalBehavior);
//                            String GeneralWellBeing = fieldsForReport[2].trim();
//                            report.setGeneralWellBeing(GeneralWellBeing);
//                            String newHabits = fieldsForReport[3].trim();
//                            report.setNewHabits(newHabits);
////                            report.setAppPhoto(photoService.);
//                            reportRepository.save(report);
//                            telegramBot.execute(new SendMessage(chatId, "Ваш отчет сохранен,следующий отчет отправьте завтра"));
//
//                        } else if (fieldsForReport1.length == 4) {
//
//                            String ration = fieldsForReport1[0].trim();
//                            report.setRation(ration);
//                            String animalBehavior = fieldsForReport1[1].trim();
//                            report.setAnimalBehavior(animalBehavior);
//                            String GeneralWellBeing = fieldsForReport1[2].trim();
//                            report.setGeneralWellBeing(GeneralWellBeing);
//                            String newHabits = fieldsForReport1[3].trim();
//                            report.setNewHabits(newHabits);
//                            reportRepository.save(report);
//                            telegramBot.execute(new SendMessage(chatId, "Ваш отчет сохранен,следующий отчет отправьте завтра"));
//
//                        }
                    }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    public static boolean checkUrl(String s) {
        String regex = "^\\+?[0-9\\-\\s]*$";
        return s != null && s.matches(regex);
    }

}




