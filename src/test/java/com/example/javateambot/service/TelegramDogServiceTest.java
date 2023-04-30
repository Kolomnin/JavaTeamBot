package com.example.javateambot.service;

import com.example.javateambot.telegramException.TelegramApiException;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TelegramDogServiceTest {

    @Test
    public void testSendWelcomeMessage() throws TelegramApiException {
        // arrange
        Long chatId = 12345L;
        TelegramBot telegramBotMock = Mockito.mock(TelegramBot.class);
        TelegramDogService telegramDogService = new TelegramDogService(telegramBotMock);

        // act
        telegramDogService.sendWelcomeMessage(chatId);

        // assert
        Mockito.verify(telegramBotMock).execute(Mockito.any(SendMessage.class));
    }

    @Test
    public void testSendFirstTimeMessage() throws TelegramApiException {
        // arrange
        Long chatId = 12345L;
        TelegramBot telegramBotMock = Mockito.mock(TelegramBot.class);
        TelegramDogService telegramDogService = new TelegramDogService(telegramBotMock);


        // act
        telegramDogService.sendFirstTimeMessage(chatId);

        // assert
        Mockito.verify(telegramBotMock).execute(Mockito.any(SendMessage.class));
    }

    @Test
    public void testDescriptionOfShelter() {
        // arrange
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expectedDescription = "Наш приют The Best Shelter — это приют для собак и кошек, в котором волонтеры следят за здоровьем животных, выгуливают их, ухаживают за ними, а также помогают найти им новый дом";

        // act
        String actualDescription = telegramDogService.descriptionOfShelter();

        // assert
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void infoAboutShelter() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = "Наш приют находится по адресу: г.Москва, 5112-й Проектируемый пр-д, стр. 1-3, 109383.\n" +
                "Как добраться: От метро Печатники идёт маршрутка № 438 до остановки «Батюнинский проезд» " +
                "(конечная). Оттуда 10 минут пешком до нашего приюта.\n" +
                "Режим работы: пн-пт с 9:00 до 21:00; сб, вс - выходные";
        assertEquals(expected, telegramDogService.infoAboutShelter());
    }

    @Test
    void safetyRecommendations() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected ="Обязательно предупреждайте о приезде в наш приют - без сопровождения волонтёра вас не пустят на территорию приюта.\n" +
                "Всех гостей встречает и сопровождает один из наших волонтёров.\n" +
                "Мы очень рады вашим визитам, поэтому сделаем всё, чтобы у вас не возникло проблем на проходной.\n" +
                "Администрация просит нас сообщать, что приедут гости и в каком количестве, так что лучше договориться о визите заранее.";
                assertEquals(expected, telegramDogService.safetyRecommendations());
    }

    @Test
    void recordingContactData() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String firstName = "John";
        String lastName = "Doe";
        String numberUser = "+1234567890";
        String expected = firstName + " " + lastName + ", Ваш номер телефона " + numberUser + " записан";
        String actual = telegramDogService.recordingContactData(firstName, lastName, numberUser);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void callVolunteer() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = "К сожалению, я не могу помочь Вам в решении вопроса. Сейчас Вам ответит оператор.";
        String actual = telegramDogService.callVolunteer();
        Assertions.assertEquals(expected, actual);
    }

//    @Test
//    void sendRulesAndDocsMessage() {
//        TelegramDogService telegramDogService = new TelegramDogService(null);
//        String expected =
//                assertEquals(expectedDescription, actualDescription);
//    }

    @Test
    void rulesForDating() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = " Правила знакомства с собакой:\n" +
                "1. Спросите разрешения у сотрудников приюта перед тем, как подойти к собаке. Они могут дать вам дополнительную информацию о поведении собаки и помочь с выбором подходящего питомца.\n"+
                "2. Подойдите к клетке собаки медленно и тихо, чтобы не пугать ее. Если собака выглядит напуганной или злой, не пытайтесь подходить к ней.\n" +
                "3. Разговаривайте с собакой мягким и успокаивающим голосом, чтобы она могла узнать ваш голос и начать доверять вам.\n" +
                "4. Если собака показывает интерес к вам, вы можете попросить сотрудника приюта разрешения на прогулку с ней в безопасной зоне.\n" +
                "5. Во время прогулки следуйте инструкциям сотрудника приюта и не позволяйте собаке подходить к другим собакам или людям  \n";
                assertEquals(expected, telegramDogService.rulesForDating());
    }

    @Test
    void listOfDocuments() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = "Чтобы забрать собаку из приюта необходимо приготовить следующий пакет документов:\n" +
                "1. Паспорт или иной документ, удостоверяющий личность.\n" +
                "\n" +
                "2. Документ, подтверждающий право собственности на жилье (квартиру или дом),в котором будет проживать собака. Это может быть, например,копия свидетельства о праве собственности или договор аренды жилья.\n" +
                "\n" +
                "3. Документ, подтверждающий финансовую способность содержать собаку.Это может быть выписка из банка или трудовой договор, который подтверждает достаточный уровень дохода.\n" +
                "\n" +
                "4.Заполненная анкета на усыновление животного из приюта.\n" +
                "\n" +
                "5. Справка от ветеринара об отсутствии заболеваний, которые могут передаваться на других животных.\n" +
                "\n" +
                "6. Подписанный договор на усыновление собаки из приюта, который подписывается между приютом и новым владельцем собаки";
                assertEquals(expected, telegramDogService.listOfDocuments());
    }
//
    @Test
    void recommendationsForTransportation() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = "Для комфортной транспортировки животного необходимо:\n" +
                "\n" +
                "1.Подходящий контейнер: животное должно находиться в контейнере, который соответствует его размеру,весу и виду. Контейнер должен быть достаточно прочным,и достаточно вентилируемым,чтобы обеспечить животному достаточный доступ к свежему воздуху.\n" +
                "\n" +
                "2. Безопасность: контейнер должен быть безопасным для животного.Для этого необходимо убедиться, что в контейнере нет острых предметов или других опасных предметов,которые могут нанести вред животному.\n" +
                "\n" +
                "3. Комфорт: животное должно находиться в комфортных условиях.Необходимо обеспечить достаточный доступ к воде и пище,а также мягкую подстилку или коврик для обеспечения комфорта животному во время транспортировки.\n";
                assertEquals(expected, telegramDogService.recommendationsForTransportation());
    }

    @Test
    void homeImprovementForPuppy() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = "Для обустройства дома щенка необходимо:\n" +
                "1. Место для отдыха: Щенок должен иметь свое место для отдыха, где он может спокойно спать и отдыхать. Это может быть специальная корзина, кровать или подушка.\n" +
                "\n" +
                "2. Место для еды и воды: Щенок должен иметь место, где он будет кушать и пить воду. Это может быть специальный кормушка или миска.\n" +
                "\n" +
                "3. Игрушки: Щенку нужно много играть, чтобы он мог развиваться и расти здоровым. Нужно предоставить щенку игрушки, которые помогут ему заниматься физическими упражнениями и развивать интеллект.\n" +
                "\n" +
                "4. Туалет: Щенок должен знать, где ему нужно справлять свои нужды. Это может быть специальный пеленальный мат или лоток для собак.\n" +
                "\n" +
                "5. Прогулки: Щенок должен выходить на прогулки, чтобы он мог изучать окружающую среду, учиться социализироваться и получать достаточно физической активности.\n" +
                "\n" +
                "6. Уход за шерстью и здоровьем: Щенку необходимо регулярно чистить зубы, обрезать когти, ухаживать за шерстью и проверять его здоровье. Для этого нужно иметь специальные инструменты и средства гигиены.\n" +
                "\n" +
                "7. Обучение: Щенок должен учиться правильно вести себя дома и на улице. Для этого необходимо обучать его командам, правилам поведения и социализировать его.\n" +
                "\n" +
                "8. Убедитесь, что вы имеете все необходимые вещи для обустройства дома для вашего щенка, чтобы он мог чувствовать себя комфортно и здоровым."
                ;
                assertEquals(expected, telegramDogService.homeImprovementForPuppy());
    }

    @Test
    void homeImprovementForAdultDog() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = "Для обустройства дома взрослой собаки необходимо:\n" +
                "* Место для отдыха: Взрослая собака должна иметь свое место для отдыха, где она может спокойно спать и отдыхать. Это может быть специальная корзина, кровать или подушка.Место для еды и воды: Взрослая собака должна иметь место, где она будет кушать и пить воду. Это может быть специальный кормушка или миска.\n" +
                "\n" +
                "* Игрушки: Взрослая собака также нуждается в игрушках, которые помогут ей заниматься физическими упражнениями и развивать интеллект.\n" +
                "\n" +
                "* Туалет: Взрослая собака должна знать, где ей нужно справлять свои нужды. Это может быть специальный лоток или выгульный дворик.\n" +
                "\n" +
                "* Прогулки: Взрослая собака должна выходить на прогулки, чтобы она могла получать достаточно физической активности, социализироваться и изучать окружающую среду.\n" +
                "\n" +
                "* Уход за шерстью и здоровьем: Взрослая собака также нуждается в регулярном уходе за шерстью и здоровьем, включая чистку зубов, обрезку когтей и проверку здоровья. Для этого нужно иметь специальные инструменты и средства гигиены.\n" +
                "\n" +
                "* Обучение: Взрослая собака может нуждаться в обучении или переобучении, особенно если она только что присоединилась к вашей семье. Обучение может включать в себя команды, правила поведения и социализацию.\n";
                assertEquals(expected, telegramDogService.homeImprovementForAdultDog());
    }
//
    @Test
    void homeImprovementForDogWithDisabilities() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = "Для обустройства дома собаки с ограниченными возможностями необходимо:\n" +
                "* Обеспечить безопасность. Убедитесь, что дом не содержит ничего, что может представлять опасность для собаки, особенно если она имеет проблемы с движением. Убедитесь, что нет лестниц, выступающих предметов, острых углов и т.д.\n" +
                "\n" +
                "* Создать комфортное место для отдыха. Убедитесь, что у собаки есть место, где она может отдохнуть и поспать. Это может быть мягкое место на полу, подушка или кровать для собаки.\n"+
                "\n"+
                "* Изучить диету собаки. Если у собаки есть проблемы со здоровьем, возможно, ей понадобится специальная диета. Обсудите это со своим ветеринаром.\\n\" +\n" +
                "\n" +
                "* Разработать программу занятий. Если у собаки есть ограничения в движении, возможно, ей потребуется специальная программа занятий, которая поможет ей оставаться активной и здоровой. Обсудите это со своим ветеринаром или тренером по собакам.\\n\" +\n" +
                "\n" +
                "* Обеспечить доступ к питьевой воде и корму. Убедитесь, что у собаки всегда есть доступ к свежей питьевой воде и корму.\\n\" +\n" +
                "\n" +
                "* Обеспечить необходимую медицинскую помощь. Если у собаки есть физические проблемы, ей может потребоваться дополнительная медицинская помощь, например, регулярные посещения ветеринара, лекарства и т.д. Следуйте рекомендациям ветеринара.\\n\" +\n" +
                "\n" +
                "* Обеспечить удобную среду обитания. Обеспечьте достаточное количество места для движения собаки внутри дома, убедитесь, что ее поводок и ошейник соответствуют ее размеру и физическим возможностям.\\n\" +\n" +
                "\n" +
                "* Регулярно проводить гигиенические процедуры. Убедитесь, что вы регулярно чистите место, где живет собака, и занимаетесь ее гигиеной (например, регулярно стригите ее когти и чистите зубы).\";\n" ;
                assertEquals(expected, telegramDogService.homeImprovementForDogWithDisabilities());
    }
//
    @Test
    void tipsFromDogHandler() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = "Наши кинологи рекоммендуют:\n" +
                "1. Подойдите к собаке медленно и осторожно, не бегите и не приближайтесь слишком близко.\n" +
                "                \n" +
                "2. Дайте собаке возможность познакомиться с вами, представьтесь ей голосом и позвольте ей нюхать вас.\n" +
                "                \n" +
                "3. Не приставайте к собаке, если она не хочет вас видеть. Если она отходит от вас или ворчит, уйдите в сторону и не настаивайте на общении.\n" +
                "                \n" +
                "4. Никогда не подходите к собаке сзади и не трогайте ее, пока она не познакомится с вами и не даст вам свое разрешение.\n" +
                "                \n" +
                "5. Никогда не поднимайте и не обнимайте собаку без ее разрешения.\n" +
                "                \n" +
                "6. Избегайте прямого взгляда и не глазируйте на собаку, это может быть воспринято ею как угроза.\n" +
                "                \n" +
                "7. Говорите с собакой мягко и дружелюбно, используйте ее имя и обращайтесь к ней уважительно.\n" +
                "                \n" +
                "8. Если вы хотите погладить собаку, позвольте ей подойти к вам самой и выразить свое желание получить ласку.\n" +
                "                \n" +
                "9. Никогда не кричите на собаку и не пугайте ее, это может привести к негативным последствиям.\n" +
                "                \n" +
                "10. Если у собаки есть владелец, обязательно спросите его разрешения, прежде чем общаться со собакой.;\n";
                assertEquals(expected, telegramDogService.TipsFromDogHandler());
    }
//
    @Test
    void infoAboutDogHandler() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = "Рекомендуем кинологов:\n" +
                "1. Ирина Петрова. Опыт работы 5 лет.\n"+
                "Специализации:Грумер.Контакты +7-913-334-93-23\n" +
                "                \n" +
                "2. Владислава. Опыт работы 8 лет\n" +
                "Специализации: зоотехния, специализация – кинология,\n" +
                "Контакты +7-913-334-93-23\n" +
                "                \n" +
                "3. Илья Игоревич. Опыт работы 2 года\n" +
                "Специализации: кинолог, кликер-дрессировка," +
                "Контакты +7-913-334-93-23";
                assertEquals(expected, telegramDogService.InfoAboutDogHandler());
    }
//
    @Test
    void reasonsForRefusal() {
        TelegramDogService telegramDogService = new TelegramDogService(null);
        String expected = "Причины отказа:\n" +
                "* Несоответствие требованиям: Например,  могут потребовать от вас доказательства того,что вы живете в доме с огороженным двором, чтобы собака не убежала. Или же, может быть, не разрешают выдачу животных людям, которые ранее были лишены права на содержание животных.\n" +
                "\n" +
                "* Несоответствие характера собаки: Некоторые собаки могут иметь поведенческие проблемы,такие как агрессивность или страх, и приют может не считать вас подходящим владельцем для такой собаки.\n" +
                "\n" +
                "* Приют может также не выдавать животных людям, которые не имеют опыта владения животными или не могут предоставить достаточное количество времени и внимания для ухода за собакой.\n" +
                "\n" +
                "* Ограниченный запас: Если приют не имеет достаточного количества собак или если они имеют очень высокий спрос, они могут отказать вам в выдаче собаки.";
                assertEquals(expected, telegramDogService.ReasonsForRefusal());
    }
//
//    @Test
//    void getReport() {
//        TelegramCatService telegramCatService = new TelegramCatService(null);
//        String expected =
//                assertEquals(expectedDescription, actualDescription);
//    }

    @Test
    void reminderAboutReport() {
    }

    @Test
    void probationPeriodPassed() {
    }

    @Test
    void probationPeriodExtended() {
    }

    @Test
    void probationPeriodNotPassed() {
    }

    @Test
    void sendMessage() {
    }
}