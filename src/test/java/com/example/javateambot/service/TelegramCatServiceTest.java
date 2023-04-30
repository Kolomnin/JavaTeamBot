package com.example.javateambot.service;

import com.example.javateambot.telegramException.TelegramApiException;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class TelegramCatServiceTest {

    @Test
    void sendWelcomeMessage() {
    }

    @Test
    public void testSendWelcomeMessage() throws TelegramApiException {
        // arrange
        Long chatId = 12345L;
        TelegramBot telegramBotMock = Mockito.mock(TelegramBot.class);
        TelegramCatService telegramCatService = new TelegramCatService(telegramBotMock);

        // act
        telegramCatService.sendWelcomeMessage(chatId);

        // assert
        Mockito.verify(telegramBotMock).execute(Mockito.any(SendMessage.class));
    }

    @Test
    public void testSendFirstTimeMessage() throws TelegramApiException {
        // arrange
        Long chatId = 12345L;
        TelegramBot telegramBotMock = Mockito.mock(TelegramBot.class);
        TelegramCatService telegramCatService = new TelegramCatService(telegramBotMock);

        // act
        telegramCatService.sendFirstTimeMessage(chatId);

        // assert
        Mockito.verify(telegramBotMock).execute(Mockito.any(SendMessage.class));
    }

    @Test
    public void testDescriptionOfShelter() {
        // arrange
        TelegramCatService telegramCatService = new TelegramCatService(null);
        String expectedDescription = "Наш приют The Best Shelter — это приют для собак и кошек, в котором волонтеры следят за здоровьем животных, выгуливают их, ухаживают за ними, а также помогают найти им новый дом";

        // act
        String actualDescription = telegramCatService.descriptionOfShelter();

        // assert
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testInfoAboutShelter() {
        // arrange
        TelegramCatService telegramCatService = new TelegramCatService(null);
        String expectedInfo = "Наш приют находится по адресу: г.Москва, 5112-й Проектируемый пр-д, стр. 1-3, 109383.\nКак добраться: От метро Печатники идёт маршрутка № 438 до остановки «Батюнинский проезд» (конечная). Оттуда 10 минут пешком до нашего приюта.\nРежим работы: пн-пт с 9:00 до 21:00; сб, вс - выходные";

        // act
        String actualInfo = telegramCatService.infoAboutShelter();

        // assert
        assertEquals(expectedInfo, actualInfo);
    }

    @Test
    public void testSafetyRecommendations() {
        TelegramCatService telegramCatService = new TelegramCatService(null);
        String expected = "Обязательно предупреждайте о приезде в наш приют - без сопровождения волонтёра вас не пустят на территорию приюта.\n" +
                "Всех гостей встречает и сопровождает один из наших волонтёров.\n" +
                "Мы очень рады вашим визитам, поэтому сделаем всё, чтобы у вас не возникло проблем на проходной.\n" +
                "Администрация просит нас сообщать, что приедут гости и в каком количестве, так что лучше договориться о визите заранее.";
        String actual = telegramCatService.safetyRecommendations();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testRecordingContactData() {
        TelegramCatService telegramCatService = new TelegramCatService(null);

        String firstName = "John";
        String lastName = "Doe";
        String numberUser = "+1234567890";
        String expected = firstName + " " + lastName + ", Ваш номер телефона " + numberUser + " записан";
        String actual = telegramCatService.recordingContactData(firstName, lastName, numberUser);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCallVolunteer() {
        TelegramCatService telegramCatService = new TelegramCatService(null);
        String expected = "К сожалению, я не могу помочь Вам в решении вопроса. Сейчас Вам ответит оператор.";
        String actual = telegramCatService.callVolunteer();
        Assertions.assertEquals(expected, actual);
    }

//    @Test
//    public void testSendRulesAndDocsMessage() {
//        TelegramCatService telegramCatService = new TelegramCatService(null);
//        String expected = "Что Вам подсказать:\n" +
//                "1. Правила знакомства с котом до того, как можно забрать ее из приюта\n" +
//                "2. Список документов, необходимый, чтобы забрать питомца\n" +
//                "3. Список рекомендаций по транспортировке животного\n" +
//                "4. Список рекомендаций по обустройству дома для котенка\n" +
//                "5. список рекомендаций по обустройству дома для кота" +
//                "6. Список рекомендаций по обустройству дома для кота с ограниченными возможностями\n" +
//                "7. Советы кинолога по первичному общению с котом\n" +
//                "8. Рекомендации по проверенным кинологам для дальнейшего обращения к ним\n" +
//                "9. Список причин, почему могут отказать и не дать забрать кота из приюта\n" +
//                "10. Изменить контактные данные\n" +
//                "11. Позвать оператора";
//        Long chatId = 123456L;
//        assertEquals(expected,)
//        // Test passes if no exceptions are thrown
//    }

    @Test
    public void testRulesForDating() {
        TelegramCatService telegramCatService = new TelegramCatService(null);

        String expected = " Правила знакомства с котом:\n" +
                "1. Дайте коту время на адаптацию: Не пытайтесь сразу потискать кота или гладить его, дайте ему самому решать, когда он будет готов подойти к вам.\n" +
                "2. Говорите спокойно и мягко: Используйте мягкий тон голоса, чтобы кот чувствовал себя более комфортно\n." +
                "3. Используйте привлекательные запахи: Привлеките внимание кота с помощью запахов, которые ему нравятся, например, котовой мяты или кошачьей мочи.\n" +
                "4. Не пугайте кота: Избегайте громких звуков, быстрых движений и других факторов, которые могут испугать кота\n" +
                "5. Предложите еду: Коты обожают еду, поэтому можно предложить ему лакомство, чтобы привлечь его внимание.  \n" +
                "6. Дайте коту возможность исследовать: Коты любят исследовать новые места, поэтому дайте ему возможность ознакомиться с вашим домом или квартирой \n";

        // Act
        String result = telegramCatService.rulesForDating();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void rulesForTransportation() {
        TelegramCatService telegramCatService = new TelegramCatService(null);
        String expected = "Для комфортной транспортировки животного необходимо:\n" +
                "\n" +
                "1.Подходящий контейнер: животное должно находиться в контейнере, который соответствует его размеру,весу и виду. Контейнер должен быть достаточно прочным,и достаточно вентилируемым,чтобы обеспечить животному достаточный доступ к свежему воздуху.\n" +
                "\n" +
                "2. Безопасность: контейнер должен быть безопасным для животного.Для этого необходимо убедиться, что в контейнере нет острых предметов или других опасных предметов,которые могут нанести вред животному.\n" +
                "\n" +
                "3. Комфорт: животное должно находиться в комфортных условиях.Необходимо обеспечить достаточный доступ к воде и пище,а также мягкую подстилку или коврик для обеспечения комфорта животному во время транспортировки.\n";
                assertEquals(expected, telegramCatService.recommendationsForTransportation());

    }

    @Test
    public void testListOfDocuments() {
        TelegramCatService telegramCatService = new TelegramCatService(null);
        String expected = "Чтобы забрать кота из приюта необходимо приготовить следующий пакет документов:\n" +
                "1. Паспорт или иной документ, удостоверяющий личность.\n" +
                "\n" +
                "2. Документ, подтверждающий право собственности на жилье (квартиру или дом),в котором будет проживать животное. Это может быть, например,копия свидетельства о праве собственности или договор аренды жилья.\n" +
                "\n" +
                "3. Документ, подтверждающий финансовую способность содержать ,кошку-кота.Это может быть выписка из банка или трудовой договор, который подтверждает достаточный уровень дохода.\n" +
                "\n" +
                "4.Заполненная анкета на усыновление животного из приюта.\n" +
                "\n" +
                "5. Справка от ветеринара об отсутствии заболеваний, которые могут передаваться на других животных.\n" +
                "\n" +
                "6. Подписанный договор на усыновление кота из приюта, который подписывается между приютом и новым владельцем животного";

        Assertions.assertEquals(expected, telegramCatService.listOfDocuments());
    }
//
    @Test
    void recommendationsForPuppy() {
        TelegramCatService telegramCatService = new TelegramCatService(null);

        String expected = "Для обустройства дома котенка необходимо:\n" +
                "1. Кормушка и миски для воды: убедитесь, что у котенка есть доступ к свежей воде и питательной пище. Расположите кормушку и миски для воды в удобном месте, которое котенок сможет легко найти\n" +
                "\n" +
                "2. Лоток: необходимо обеспечить котенку место для естественных нужд. Купите лоток, который подходит для размера котенка, и разместите его в тихом месте дома\n" +
                "\n" +
                "3. Игрушки: котята любят играть и исследовать окружающий мир. Купите несколько игрушек, чтобы помочь им заняться их любимыми играми.\n" +
                "\n" +
                "4. Когтеточка: котенок будет царапать, поэтому купите когтеточку, чтобы предоставить ему место для точения когтей.\n" +
                "\n" +
                "5. Уборка: убедитесь, что дом чист и безопасен для котенка. Уберите все опасные предметы, которые могут навредить котенку, и держите дом в чистоте.\n" + "\n" +
                "6. Место для отдыха: обеспечьте котенку место для отдыха, где он может чувствовать себя комфортно. Это может быть корзина или кровать для котов.\n" +
                "\n" +
                "7. Проверьте окна и двери: убедитесь, что окна и двери защищены, чтобы котенок не мог убежать на улицу или не попал в опасную ситуацию.\n" +
                "\n" +
                "8. Посетите ветеринара: необходимо проконсультироваться с ветеринаром и убедиться, что котенок здоров и получил все необходимые прививки."
        ;
        Assertions.assertEquals(expected, telegramCatService.homeImprovementForPuppy());

    }
//
    @Test
    void homeImprovementAdultDog() {
        TelegramCatService telegramCatService = new TelegramCatService(null);

        String expected = "Для обустройства дома взрослого кота необходимо:\n" +
                "1. Кормушка и миски для воды: убедитесь, что у кота есть доступ к свежей воде и питательной пище. Расположите кормушку и миски для воды в удобном месте, которое кота сможет легко найти\n" +
                "\n" +
                "2. Лоток: необходимо обеспечить кота место для естественных нужд. Купите лоток, который подходит для размера кота, и разместите его в тихом месте дома\n" +
                "\n" +
                "3. Игрушки: кошки любят играть и исследовать окружающий мир. Купите несколько игрушек, чтобы помочь им заняться их любимыми играми.\n" +
                "\n" +
                "4. Когтеточка: кот будет царапать, поэтому купите когтеточку, чтобы предоставить ему место для точения когтей.\n" +
                "\n" +
                "5. Уборка: убедитесь, что дом чист и безопасен для кота. Уберите все опасные предметы, которые могут навредить коту, и держите дом в чистоте.\n" +
                "\n" +
                "6. Место для отдыха: обеспечьте коту место для отдыха, где он может чувствовать себя комфортно. Это может быть корзина или кровать для котов.\n" +
                "\n" +
                "7. Проверьте окна и двери: убедитесь, что окна и двери защищены, чтобы кот не мог убежать на улицу или не попал в опасную ситуацию.\n" +
                "\n" +
                "8. Посетите ветеринара: необходимо проконсультироваться с ветеринаром и убедиться, что котенок здоров и получил все необходимые прививки."
        ;
        Assertions.assertEquals(expected, telegramCatService.homeImprovementForAdultDog());

    }


    @Test
    void homeImprovementForDogWithDisabilities() {
        TelegramCatService telegramCatService = new TelegramCatService(null);


        String expected = "Для обустройства дома кота с ограниченными возможностями необходимо:\n" +
                "* Обеспечить безопасность. Убедитесь, что дом не содержит ничего, что может представлять опасность для кота, особенно если она имеет проблемы с движением. Убедитесь, что нет лестниц, выступающих предметов, острых углов и т.д.\n" +
                "\n" +
                "* Создать комфортное место для отдыха. Убедитесь, что у кота есть место, где она может отдохнуть и поспать. Это может быть мягкое место на полу, подушка или кровать для кота.\n"+
                "\n"+
                "* Изучить диету кота. Если у кота есть проблемы со здоровьем, возможно, ей понадобится специальная диета. Обсудите это со своим ветеринаром.\\n\" +\n" +
                "\n" +
                "* Разработать программу занятий. Если у кота есть ограничения в движении, возможно, ей потребуется специальная программа занятий, которая поможет ей оставаться активной и здоровой. Обсудите это со своим ветеринаром или тренером по собакам.\\n\" +\n" +
                "\n" +
                "* Обеспечить доступ к питьевой воде и корму. Убедитесь, что у кота всегда есть доступ к свежей питьевой воде и корму.\\n\" +\n" +
                "\n" +
                "* Обеспечить необходимую медицинскую помощь. Если у кота есть физические проблемы, ей может потребоваться дополнительная медицинская помощь, например, регулярные посещения ветеринара, лекарства и т.д. Следуйте рекомендациям ветеринара.\\n\" +\n" +
                "\n" +
                "* Обеспечить удобную среду обитания. Обеспечьте достаточное количество места для движения кота внутри дома,\n" +
                "\n" +
                "* Регулярно проводить гигиенические процедуры. Убедитесь, что вы регулярно чистите место, где живет кот, и занимаетесь ее гигиеной \n" ;
        Assertions.assertEquals(expected, telegramCatService.homeImprovementForDogWithDisabilities());

    }
//
    @Test
    void tipsFromDogHandler() {
        TelegramCatService telegramCatService = new TelegramCatService(null);
        String expected = "Зоопсихологи рекомендуют рекоммендуют:\n" +
                "1. Учитесь понимать язык тела кошек. Кошки выражают свои чувства и намерения через позы тела, мимику и мяуканье. Изучите их язык тела, чтобы понимать, когда они рады, когда находятся в напряжении или страхе." +
                "                \n" +
                "2. Будьте терпеливы и почитайте кошку. Некоторые кошки нуждаются в большем времени, чтобы привыкнуть к новым людям и среде. Дайте им время, чтобы они могли исследовать свою новую окружающую среду и подойти к вам, когда они будут готовы.\n" +
                "                \n" +
                "3. Не используйте физическую силу. Кошки не реагируют на физическое наказание и могут стать защитнически настроенными, если их обижают. Вместо этого используйте положительное подкрепление, такое как лакомства и игрушки, чтобы поощрять желаемое поведение.\n" +
                "                \n" +
                "4. Учитесь играть с кошками. Кошки любят играть, и игры могут помочь им заняться физической активностью и укрепить связь между вами и вашей кошкой.\n" +
                "                \n" +
                "5. Оставайтесь спокойными и уверенными. Кошки чувствуют эмоции своих владельцев и могут стать напряженными, если вы волнуетесь или беспокоитесь. Постарайтесь оставаться спокойными и уверенными во время общения с вашей кошкой.\n" +
                "                \n" +
                "Помните, что каждая кошка уникальна и может иметь свои собственные предпочтения и потребности. Общение с вашей кошкой должно быть индивидуальным и адаптированным к ее потребностям и характеру.";
                assertEquals(expected,telegramCatService.TipsFromDogHandler());
    }
//
    @Test
    void infoAboutDogHandler() {
        TelegramCatService telegramCatService = new TelegramCatService(null);
        String expected ="Рекомендуем кинологов:\n" +
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
        assertEquals(expected,telegramCatService.InfoAboutDogHandler());
    }

    @Test
    void reasonsForRefusal() {
        TelegramCatService telegramCatService = new TelegramCatService(null);
        String expected = "Причины отказа:\n" +
                "* Несоответствие требованиям: Например,  могут потребовать от вас доказательства того,что вы живете в доме с огороженным двором, чтобы собака не убежала. Или же, может быть, не разрешают выдачу животных людям, которые ранее были лишены права на содержание животных.\n" +
                "\n" +
                "* Несоответствие характера собаки: Некоторые собаки могут иметь поведенческие проблемы,такие как агрессивность или страх, и приют может не считать вас подходящим владельцем для данного животного.\n" +
                "\n" +
                "* Приют может также не выдавать животных людям, которые не имеют опыта владения животными или не могут предоставить достаточное количество времени и внимания для ухода за собакой.\n" +
                "\n" +
                "* Ограниченный запас: Если приют не имеет достаточного количества кошек-котов или если они имеют очень высокий спрос, они могут отказать вам в выдаче кошек-котов.";
                assertEquals(expected,telegramCatService.ReasonsForRefusal());
    }
//
//    @Test
//    void getReport() {
//        TelegramCatService telegramCatService = new TelegramCatService(null);
//        String expected =
//    }
////
//    @Test
//    void reminderAboutReport() {
//        TelegramCatService telegramCatService = new TelegramCatService(null);
//        String expected =
//    }
//
//    @Test
//    void probationPeriodPassed() {
//
//        TelegramCatService telegramCatService = new TelegramCatService(null);
//        String expected =
//    }
//
//    @Test
//    void probationPeriodExtended() {
//
//        TelegramCatService telegramCatService = new TelegramCatService(null);
//        String expected =
//    }
////
//    @Test
//    void probationPeriodNotPassed() {
//
//        TelegramCatService telegramCatService = new TelegramCatService(null);
//        String expected =
//    }
//
//    @Test
//    void sendMessage() {
//        TelegramCatService telegramCatService = new TelegramCatService(null);
//        String expected =
//    }
}

