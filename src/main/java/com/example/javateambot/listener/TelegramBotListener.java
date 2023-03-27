package com.example.javateambot.listener;

import com.example.javateambot.service.TelegramBotService;
import com.pengrad.telegrambot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Service
public class TelegramBotListener {
    private static final String NAME_OF_SCHELTER = "The Best Shelter";
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotListener.class);

    private static final Pattern pattern = Pattern.compile(
            "([\\d\\\\.:\\s]{16})(\\s)([А-яA-z\\s\\d,.!?:]+)");

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy HH:mm");

    private final TelegramBot telegramBot;
    private TelegramBotService telegramBotService;

    public TelegramBotListener(TelegramBot telegramBot, TelegramBotService telegramBotServiceService) {
        this.telegramBot = telegramBot;
        this.telegramBotService = telegramBotService;
    }


    public void process() {
/*
Пользователь обращается, бот выводит сообщение:

Добрый день! Добрый день! Приветствуем Вас в нашем приюте для животных NAME_OF_SCHELTER.
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

    }


}
