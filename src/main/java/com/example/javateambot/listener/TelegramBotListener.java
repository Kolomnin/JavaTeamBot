package com.example.javateambot.listener;


import com.example.javateambot.repository.*;
import com.example.javateambot.service.*;


import com.pengrad.telegrambot.model.Update;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.request.SendMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class TelegramBotListener implements UpdatesListener {


    private TelegramBot telegramBot;

   private UsersRepository usersRepository;


    private TelegramBotService telegramBotService;

   
    private DogAdoptionRepository dogAdoptionRepository;

    
    private ContactInformationRepository contactInformationRepository;

  
    private ReportRepository reportRepository;



    private SaveReportAndContactData saveReportAndContactData;


    private PhotoService photoService;


    private DogAdoptionService dogAdoptionService;
    private TelegramCatService telegramCatService;

    private final TelegramDogService telegramDogService;

 

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

    public static final String RULES_FOR_DATING_For_Cat = "Правила знакомства c котом";
    public static final String LIST_OF_DOCUMENTS1 = "Список документов для котаа";
    public static final String RECOMMENDATIONS_FOR_TRANSPORTATION1 = "кота транспортировка";
    public static final String HOME_IMPROVEMENT_FOR_PUPPY1 = "дома для кота";
    public static final String HOME_IMPROVEMENT_FOR_ADULT_CAT = "дома для взрослого кота";
    public static final String HOME_IMPROVEMENT_FOR_CAT_WITH_DISABILITIES1 = "дома для кота с ограничениями";
    public static final String TIPS_FROM_CAT_HANDLER1 = "советы кинолога для кота";
    public static final String INFO_ABOUT_CAT_HANDLER1 = "список кинологов для кота";
    public static final String REASONS_FOR_REFUSAL1 = "список причин для отказа для кота";
    public static final String SHELTER_INFO_MENU = "Меню для информации о приюте";
    public static final String HOW_TO_TAKE_DOG = "Как взять собаку из приюту";
    public static final String HOW_TO_TAKE_CAT = "Как взять кошку из приюту";

    public static final String SEND_REPORT = "Отправить отчет";
    public static final String FORM_DAILY_REPORT = "Форма ежедневного отчета";
    public static final String CALL_VOLUNTEER = "Зовем волонетра";
    public static final String RECIEVE_REPORT = "Получить отчет";
    public static final String WRITE_DOWN_CONTACT_DATA = "записать контактные данные";
    public static final String HOW_TAKE_CAT = "Как взять кошку из приюта";
    public static final String START = "/start";




    private final Logger logger = LoggerFactory.getLogger(TelegramBotListener.class);



    public TelegramBotListener(TelegramBot telegramBot, UsersRepository usersRepository,
                               TelegramBotService telegramBotService, DogAdoptionRepository dogAdoptionRepository,
                               ContactInformationRepository contactInformationRepository,
                               ReportRepository reportRepository, SaveReportAndContactData saveReportAndContactData,
                               TelegramDogService telegramDogService, PhotoService photoService,
                               DogAdoptionService dogAdoptionService,TelegramCatService telegramCatService) {
        this.telegramBot = telegramBot;
        this.usersRepository = usersRepository;
        this.telegramBotService = telegramBotService;
        this.dogAdoptionRepository = dogAdoptionRepository;
        this.contactInformationRepository = contactInformationRepository;
        this.reportRepository = reportRepository;
        this.saveReportAndContactData = saveReportAndContactData;
        this.telegramDogService = telegramDogService;
        this.photoService = photoService;
        this.dogAdoptionService = dogAdoptionService;
        this.telegramCatService = telegramCatService;
    }


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    Long chatId;

    @Override
    public int process(List<Update> updates) {

        try {
            updates.forEach(update -> {




               logger.info("Processing update: {}", update);
               //тут две кнопки приют кошки или собаки если


                        if (update.callbackQuery() != null&& update.message()==null) {
                            // обработка этапа 0

                            chatId = update.callbackQuery().message().chat().id();
                            CallbackQuery callbackQuery = update.callbackQuery();
                            String data = callbackQuery.data();


                            try {
                                System.out.println(update.message().text());

                            } catch (Exception e) {
                                System.out.println("Ошибка");
                            }


                            switch (data) {

                                case SHELTER_INFO_MENU -> telegramBotService.shelterInfo(chatId);
                                case INFO_ABOUT_SHELTER ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.descriptionOfShelter()));
                                case WORK_SCHEDULE ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.infoAboutShelter()));
                                case SAFETY_RECOMMENDATIONS ->
                                        telegramBot.execute(new SendMessage(chatId, telegramDogService.safetyRecommendations()));

                                case HOW_TO_TAKE_DOG -> telegramBotService.takeDogFromShelter(chatId);
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

                                case RULES_FOR_DATING_For_Cat ->
                                        telegramBot.execute(new SendMessage(chatId, telegramCatService.rulesForDating()));
                                case LIST_OF_DOCUMENTS1 ->
                                        telegramBot.execute(new SendMessage(chatId, telegramCatService.listOfDocuments()));
                                case RECOMMENDATIONS_FOR_TRANSPORTATION1 ->
                                        telegramBot.execute(new SendMessage(chatId, telegramCatService.recommendationsForTransportation()));
                                case HOME_IMPROVEMENT_FOR_PUPPY1 ->
                                        telegramBot.execute(new SendMessage(chatId, telegramCatService.homeImprovementForPuppy()));
                                case HOME_IMPROVEMENT_FOR_ADULT_CAT ->
                                        telegramBot.execute(new SendMessage(chatId, telegramCatService.homeImprovementForAdultDog()));
                                case HOME_IMPROVEMENT_FOR_CAT_WITH_DISABILITIES1 ->
                                        telegramBot.execute(new SendMessage(chatId, telegramCatService.homeImprovementForDogWithDisabilities()));
                                case TIPS_FROM_CAT_HANDLER1 ->
                                        telegramBot.execute(new SendMessage(chatId, telegramCatService.TipsFromDogHandler()));
                                case INFO_ABOUT_CAT_HANDLER1 ->
                                        telegramBot.execute(new SendMessage(chatId, telegramCatService.InfoAboutDogHandler()));
                                case REASONS_FOR_REFUSAL1 ->
                                        telegramBot.execute(new SendMessage(chatId, telegramCatService.ReasonsForRefusal()));

                                case SEND_REPORT -> telegramBotService.sendReport(chatId);
                                case FORM_DAILY_REPORT -> {
                                    telegramBot.execute(new SendMessage(chatId, telegramDogService.formDayReport()));
                                    telegramBotService.sendReport(chatId);
                                }

                                case RECIEVE_REPORT ->
                                        telegramBot.execute(new SendMessage(chatId, "Вышлите фото животного"));

                                case CALL_VOLUNTEER -> telegramBot.execute(new SendMessage(chatId, "Зовем волонтера"));

                                case WRITE_DOWN_CONTACT_DATA -> {
                                    telegramBot.execute(new SendMessage(chatId, telegramDogService.writeContactData()));

                                }
                                case HOW_TAKE_CAT -> {
                                    telegramBotService.catInfo(chatId);

                                }
                            }
                        }

                    if (update.message() != null) {
                    chatId = update.message().chat().id();
                    String message = update.message().text();
                    if (START.equals(message)) {
                        telegramBotService.firstMenu(chatId);
                    }
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
                        if (update.message() != null && update.message().photo() != null) {
                            // ваш код для обработки сообщения с фото
                        }
                        if (update.message() != null && update.message().photo() != null) { //

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
                        if (update.message() != null && update.message().text() != null) {
                            saveReportAndContactData.saveContactData(update.message().text(), chatId);
                        }


                        /**
                         * Это обработчик отчетов, когда нам владелец присылает отчет.
                         */
                        if (update.message() != null && update.message().text() != null) {
                            saveReportAndContactData.saveReport(update.message().text(), chatId);
                        }


               }
            );
        } catch(Exception e){
            e.printStackTrace();
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

//    public static boolean checkUrl(String s) {
//        String regex = "^\\+?[0-9\\-\\s]*$";
//        return s != null && s.matches(regex);
//    }

}






