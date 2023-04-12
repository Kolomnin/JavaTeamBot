package com.example.javateambot.service;

import com.example.javateambot.entity.User;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Service
public class TelegramService {
//    private final UserRepository userRepository;

    public TelegramService(TelegramBot telegramBot) {
//        this.userRepository = userRepository;
        this.telegramBot = telegramBot;
    }


    //КОНСУЛЬТАЦИЯ С НОВЫМ ПОЛЬЗОВАТЕЛЕМ
    private static final String NAME_OF_SCHELTER = "The Best Shelter";

    /**
     * Метод, который выводит приветственное сообщение и предлагает выбрать дальнейшие действия
     * @param chatId
     */
    private final TelegramBot telegramBot;
    public void sendWelcomeMessage(Long chatId) {
        String message = "Добрый день! Приветствуем Вас в приюте для животных \"" + NAME_OF_SCHELTER + "\".\n" +
                "Выберите пожалуйста, что Вас интересует:\n" +
                "1. Я впервые здесь и хочу узнать больше о приюте.\n" +
                "2. Я уже обращался(-лась) к Вам и хочу получить информацию о животном, которое ранее было у меня.\n" +
                "3. Я хочу отправить отчет о животном, которое я взял(-а) в этом приюте ранее.";
        telegramBot.execute(new SendMessage(chatId, message));
    }

    /**
     * Метод для пользователя, который впервые в нашем приюте.
     * Метод предлагает выбрать дальнейшие действия.
     */
    public void sendFirstTimeMessage(Long chatId) {
        String message = "Что Вам подсказать:\n" +
                "Рассказать о приюте\n" +
                "Выдать расписание работы приюта и адрес, схему проезда\n" +
                "Выдать общие рекомендации о технике безопасности на территории приюта\n" +
                "Оставить контактные данные, чтобы мы связались с Вами\n" +
                "Позвать оператора";
        telegramBot.execute(new SendMessage(chatId, message));
    }

    /**
     * Бот рассказывает о приюте.
     * @return информация о приюте
     */
    public String descriptionOfShelter() {
        return  "Наш приют " + NAME_OF_SCHELTER + " — это приют для собак, в котором волонтеры следят за " +
                         "здоровьем животных, выгуливают их, ухаживают за ними, а также помогают найти им новый дом";

    }

    /**
     * Бот выдаёт расписание работы приюта и адрес, схему проезда.
     * @return расписание работы приюта и адрес, схему проезда
     */
    public String infoAboutShelter() {
        return "Наш приют находится по адресу: г.Москва, 5112-й Проектируемый пр-д, стр. 1-3, 109383.\n" +
                "Как добраться: От метро Печатники идёт маршрутка № 438 до остановки «Батюнинский проезд» " +
                "(конечная). Оттуда 10 минут пешком до нашего приюта.\n" +
                "Режим работы: пн-пт с 9:00 до 21:00; сб, вс - выходные";
    }

    /**
     * Бот выдаёт общие рекомендации о технике безопасности на территории приюта.
     * @return общие рекомендации о технике безопасности на территории приюта
     */
    public String safetyRecommendations() {
        return "Обязательно предупреждайте о приезде в наш приют - без сопровождения волонтёра вас не пустят на территорию приюта.\n" +
                "Всех гостей встречает и сопровождает один из наших волонтёров.\n" +
                "Мы очень рады вашим визитам, поэтому сделаем всё, чтобы у вас не возникло проблем на проходной.\n" +
                "Администрация просит нас сообщать, что приедут гости и в каком количестве, так что лучше договориться о визите заранее.";
    }

    /**
     * Бот принимает и записывает контактные данные для связи.
     * @param firstName имя
     * @param lastName фамилия
     * @param numberUser номер телефона
     * @return записанные контактные данные для связи
     */
    public String recordingContactData(String firstName, String lastName, String numberUser) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setNumberUser(numberUser);
//        userRepository.save(newUser);
        return firstName + " " + lastName + ", Ваш номер телефона " + numberUser + " записан";
    }

    /**
     * Метод зовет оператора, если бот не может ответить на вопросы клиента
     * @return текст, уведомляющий о том, что боту нужна помощь оператора
     */
    public String callVolunteer() {
        return "К сожалению, я не могу помочь Вам в решении вопроса. Сейчас Вам ответит оператор.";
    }


    //КОНСУЛЬТАЦИЯ С ПОТЕНЦИАЛЬНЫМ ХОЗЯИНОМ
    /**
     * Метод для потенциального хозяина животного.
     * Метод предлагает выбрать дальнейшие действия.
     */
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

    /**
     * Бот выдаёт правила знакомства с собакой до того, как можно забрать ее из приюта.
     * @return правила знакомства с собакой
     */
    public String rulesForDating() {
        return " Прежде чем забрать ..... метод добавил, ребят, запилите текст какие должны быть правила ";
    }

    /**
     * Бот выдаёт список документов, необходимых для того, чтобы взять собаку из приюта.
     * @return список документов, необходимых для того, чтобы взять собаку из приюта
     */
    public String listOfDocuments() {
        return "Чтобы забрать собаку из приюта необходимо приготовить следующий пакет документов:\n" +
                " ..... ";
    }

    /**
     * Бот может выдать список рекомендаций по транспортировке животного.
     * @return список рекомендаций по транспортировке животного
     */
    public String recommendationsForTransportation() {
        return "Для комфортной транспортировки животного необходимо:\n" +
                " ..... ";
    }

    /**
     * Бот выдает список рекомендаций по обустройству дома для щенка.
     * @return список рекомендаций по обустройству дома для щенка
     */
    public String homeImprovementForPuppy() {
        return "Для обустройства дома щенка необходимо:\n" +
                " ..... ";
    }

    /**
     * Бот может выдать список рекомендаций по обустройству дома для взрослой собаки.
     * @return список рекомендаций по обустройству дома для взрослой собаки
     */
    public String homeImprovementForAdultDog() {
        return "Для обустройства дома взрослой собаки необходимо:\n" +
                " ..... ";
    }

    /**
     * Бот выдаёт список рекомендаций по обустройству дома для собаки с ограниченными возможностями.
     * @return список рекомендаций по обустройству дома для собаки с ограниченными возможностями
     */
    public String homeImprovementForDogWithDisabilities() {
        return "Для обустройства дома собаки с ограниченными возможностями необходимо:\n" +
                " ..... ";
    }

    /**
     * Бот выдаёт советы кинолога по первичному общению с собакой.
     * @return советы кинолога по первичному общению с собакой
     */
    public String TipsFromDogHandler() {
        return "Наши кинологи рекоммендуют:\n" +
                " ..... ";
    }

    /**
     * Бот выдаёт рекомендации по проверенным кинологам для дальнейшего обращения к ним.
     * @return рекомендации по проверенным кинологам
     */
    public String InfoAboutDogHandler() {
        return "Рекомендуем кинологов:\n" +
                " ..... ";
    }

    /**
     * Бот выдаёт список причин, почему могут отказать и не дать забрать собаку из приюта.
     * @return список причин, почему могут отказать и не дать забрать собаку из приюта
     */
    public String ReasonsForRefusal() {
        return "Причины отказа:\n" +
                " ..... ";
    }

    //ВЕДЕНИЕ ПИТОМЦА
    /**
     * Метод, который принимает отчет от хозяина питомца
     * @param text описание состояния животного: рацион животного, общее самочувствие и привыкание к новому месту, изменение в поведении*
     * @param photo фото животного
     * @return текст о принятии/непринятии отчета
     */
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

    /**
     * Метод, который проверяет дату последнего отчета.
     * Если отчета не было более 2х дней, высылает напоминание
     * @param idAnimal ID животного
     * @param lastReportDate дата последней отчетности
     */
    public void reminderAboutReport(long idAnimal, LocalDate lastReportDate) {
        //сравниваем дату отчета и сегодняшнюю, если больше 2, то отправляем напоминание
    }

    /**
     * Метод, который проверяет закончился ли испытательный период.
     * Если испытательный период окончен - направляет поздравления.
     * @param idAnimal ID животного
     * @param lastDateProbationReriod дата окончания испытательного срока
     */
    public void probationPeriodPassed(long idAnimal, LocalDate lastDateProbationReriod) {
        //сравниваем дату окончания испыт.срока и сегодняшнюю, если больше, то поздравляем
    }

    /**
     * Метод, который позволяет увеличить испытательный срок.
     * Уведомляет об увеличении испытательного срока хозяина животного.
     * @param idAnimal ID животного
     * @param numberOfDays количество дней, на которое будет увеличен испытательный срок
     * @return новую дату окончания испытательного срока
     */
    public LocalDate probationPeriodExtended(long idAnimal, int numberOfDays) {
        //прибавляем к испыт.сроку кол-во дней и уведомляем об этом по номеру владельца
        LocalDate newLastDateProbationReriod = null; //ЗАМЕНИТЬ: вместо null по ID животного находим дату окончания испыт.срока
        return newLastDateProbationReriod;
    }

    /**
     * Метод, который уведомляем, что испытательныйсрок не пройден и необходимо обратиться лично к администрации за дальнейшей инструкцией
     * @param idAnimal ID животного
     */
    public void probationPeriodNotPassed(long idAnimal) {
        //уведомляем, что испыт.срок не пройден и необходимо обратиться лично к администрации за дальнейшей инструкцией
    }

    /**
     * Метод, который будет отправлять сообщения
     * @param chatId ID пользователя
     * @param s текст отправки
     */
    public void sendMessage(Long chatId, String s) {
    }

}



