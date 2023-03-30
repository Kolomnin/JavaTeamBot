package com.example.javateambot.service;

import com.example.javateambot.entity.User;
//import com.example.javateambot.repository.UserRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
public class TelegramBotService {
//    private final UserRepository userRepository;

    public TelegramBotService(TelegramBot telegramBot) {
//        this.userRepository = userRepository;
        this.telegramBot = telegramBot;
    }


    //КОНСУЛЬТАЦИЯ С НОВЫМ ПОЛЬЗОВАТЕЛЕМ
    private static final String NAME_OF_SCHELTER = "The Best Shelter";

    private final TelegramBot telegramBot;
    public void sendWelcomeMessage(Long chatId) {
        String message =  "Добрый день! Приветствуем Вас в приюте для животных \"" + NAME_OF_SCHELTER + "\".\n" +
                "Выберите пожалуйста, что Вас интересует:\n" +
                "1. Я впервые здесь и хочу узнать больше о приюте.\n" +
                "2. Я уже обращался(-лась) к Вам и хочу получить информацию о животном, которое ранее было у меня.\n" +
                "3. Я хочу отправить отчет о животном, которое я взял(-а) в этом приюте ранее.";
        telegramBot.execute(new SendMessage(chatId, message));
    }

    //Бот приветствует пользователя.
    public void sendFirstTimeMessage(Long chatId) {
        String message = "Что Вам подсказать:\n" +
                "1. Рассказать о приюте\n" +
                "2. Выдать расписание работы приюта и адрес, схему проезда\n" +
                "3. Выдать общие рекомендации о технике безопасности на территории приюта\n" +
                "4. Оставить контактные данные, чтобы мы связались с Вами\n" +
                "5. Позвать оператора";
        telegramBot.execute(new SendMessage(chatId, message));
    }

    //Бот может рассказать о приюте.
    public String descriptionOfShelter() {
        return "Наш приют " + NAME_OF_SCHELTER + " — это приют для собак, в котором волонтеры следят за здоровьем животных, выгуливают их, ухаживают за ними, а также помогают найти им новый дом";

    }

    //Бот может выдать расписание работы приюта и адрес, схему проезда.
    public String infoAboutShelter() {
        return "Наш приют находится по адресу: г.Москва, 5112-й Проектируемый пр-д, стр. 1-3, 109383.\n" +
                "Как добраться: От метро Печатники идёт маршрутка № 438 до остановки «Батюнинский проезд» (конечная). Оттуда 10 минут пешком до нашего приюта.\n" +
                "Режим работы: пн-пт с 9:00 до 21:00; сб, вс - выходные";
    }

    //Бот может выдать общие рекомендации о технике безопасности на территории приюта.
    public String safetyRecommendations() {
        return "Обязательно предупреждайте о приезде в наш приют - без сопровождения волонтёра вас не пустят на территорию приюта.\n" +
                "Всех гостей встречает и сопровождает один из наших волонтёров.\n" +
                "Мы очень рады вашим визитам, поэтому сделаем всё, чтобы у вас не возникло проблем на проходной.\n" +
                "Администрация просит нас сообщать, что приедут гости и в каком количестве, так что лучше договориться о визите заранее.";
    }

    //Бот может принять и записать контактные данные для связи.
    public String recordingContactData(String firstName, String lastName, String numberUser) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setNumberUser(numberUser);
//        userRepository.save(newUser);
        return firstName + " " + lastName + ", Ваш номер телефона " + numberUser + " записан";
    }

    //Если бот не может ответить на вопросы клиента, то можно позвать волонтера.
    public String callVolunteer() {
        return "К сожалению, я не могу помочь Вам в решении вопроса. Сейчас Вам ответит оператор.";
    }


    //КОНСУЛЬТАЦИЯ С ПОТЕНЦИАЛЬНЫМ ХОЗЯИНОМ
    //Бот приветствует пользователя.
    public void sendRulesAndDocsMessage(Long chatId) {
        String message = "Что Вам подсказать:\n" +
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
        telegramBot.execute(new SendMessage(chatId, message));
    }

    //Бот может выдать правила знакомства с собакой до того, как можно забрать ее из приюта.
    public String rulesForDating() {
        return " Прежде чем забрать ..... ";
    }

    //Бот может выдать список документов, необходимых для того, чтобы взять собаку из приюта.
    public String listOfDocuments() {
        return "Чтобы забрать собаку из приюта необходимо приготовить следующий пакет документов:\n" +
                " ..... ";
    }

    //Бот может выдать список рекомендаций по транспортировке животного.
    public String recommendationsForTransportation() {
        return "Для комфортной транспортировки животного необходимо:\n" +
                " ..... ";
    }

    //Бот может выдать список рекомендаций по обустройству дома для щенка.
    public String homeImprovementForPuppy() {
        return "Для обустройства дома щенка необходимо:\n" +
                " ..... ";
    }

    //Бот может выдать список рекомендаций по обустройству дома для взрослой собаки.
    public String homeImprovementForAdultDog() {
        return "Для обустройства дома взрослой собаки необходимо:\n" +
                " ..... ";
    }

    //Бот может выдать список рекомендаций по обустройству дома для собаки с ограниченными возможностями (зрение, передвижение).
    public String homeImprovementForDogWithDisabilities() {
        return "Для обустройства дома собаки с ограниченными возможностями необходимо:\n" +
                " ..... ";
    }

    //Бот может выдать советы кинолога по первичному общению с собакой.
    public String TipsFromDogHandler() {
        return "Наши кинологи рекоммендуют:\n" +
                " ..... ";
    }

    //Бот может выдать рекомендации по проверенным кинологам для дальнейшего обращения к ним.
    public String InfoAboutDogHandler() {
        return "Рекомендуем кинологов:\n" +
                " ..... ";
    }

    //Бот может выдать список причин, почему могут отказать и не дать забрать собаку из приюта.
    public String ReasonsForRefusal() {
        return "Причины отказа:\n" +
                " ..... ";
    }

    //ВЕДЕНИЕ ПИТОМЦА
    public String getReport(String text, MultipartFile photo) {
        if (text != null && photo != null) {
            //загрузить фото и текст в базу animals, а также установить сегодняшнюю дату отчетности
            return "Отчет принят";
        }
        if (text == null) {
            return "Загрузите текст!";
        }
        if (photo == null) {
            return "Загрузите фото!";
        }
        return "";
    }

    public void reminderAboutReport(long idAnimal, LocalDate lastReportDate) {
        //сравниваем дату отчета и сегодняшнюю, если больше 2, то отправляем напоминание
    }

    public void probationPeriodPassed(long idAnimal, LocalDate lastDateProbationReriod) {
        //сравниваем дату окончания испыт.срока и сегодняшнюю, если больше, то поздравляем
    }

    public void probationPeriodExtended(long idAnimal, int numberOfDays) {
        //прибавляем к испыт.сроку кол-во дней и уведомляем об этом по номеру владельца
    }

    public void probationPeriodNotPassed(long idAnimal) {
//уведомляем, что испыт.срок не пройден и, необходимо обратиться лично кадминистрации за дельнейшей инструкцией
    }

    public void sendMessage(Long chatId, String s) {
    }
}

