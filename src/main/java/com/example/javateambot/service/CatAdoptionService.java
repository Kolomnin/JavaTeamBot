package com.example.javateambot.service;

import com.example.javateambot.entity.AdoptedCats;
import com.example.javateambot.entity.AdoptedDogs;
import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.CatAdoptionRepository;
import com.example.javateambot.repository.CatsInShelterRepository;
import com.example.javateambot.repository.UsersRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class CatAdoptionService {

    private CatsInShelterRepository catsInShelterRepository;

    private UsersRepository usersRepository;

    private CatAdoptionRepository catAdoptionRepository;

    private TelegramBot telegramBot;

    @Autowired
    public CatAdoptionService(CatsInShelterRepository catsInShelterRepository, UsersRepository usersRepository, CatAdoptionRepository catAdoptionRepository, TelegramBot telegramBot) {
        this.catsInShelterRepository = catsInShelterRepository;
        this.usersRepository = usersRepository;
        this.catAdoptionRepository = catAdoptionRepository;
        this.telegramBot = telegramBot;
    }

// работает с этим кодом, но в нем не красиво добавляется в свагере
    public AdoptedCats adoptionCat2(Long userID, Long catId, AdoptedCats adoptedCats) {

        if (catsInShelterRepository.findById(catId).isPresent()||usersRepository.findById(userID).isPresent()){

            adoptedCats.setUsers(usersRepository.findById(userID).orElseThrow());
            adoptedCats.setCats(catsInShelterRepository.findById(catId).orElseThrow());
            adoptedCats.setLastDateProbationPeriod(LocalDate.now().plusDays(30));
            adoptedCats.setAdoptionDate(LocalDate.now());

        }

        return catAdoptionRepository.save(adoptedCats);
    }

    public Boolean checkChatId(Long chatId) {
        if (Objects.equals(usersRepository.findByChatId(chatId).getChatId(), chatId)) {
            return true ;
        } else return false;
    }

    public void increaseProbationPeriodCat(Long idCat, int daysToIncrease) {
        //если указано дней меньше 14, то увеличение будет на 14, иначе - на 30 дней
        if (daysToIncrease <= 14) {
            daysToIncrease = 14;
        } else {
            daysToIncrease = 30;
        }

        //увеличиваем дату
        AdoptedCats cat = catAdoptionRepository.findById(idCat).orElseThrow();
        LocalDate newDate = cat.getLastDateProbationPeriod().plusDays(daysToIncrease);
        cat.setLastDateProbationPeriod(newDate);
        catAdoptionRepository.save(cat);

        //сообщаем пользователю об этом
        String message = "Ваш испытательный срок был продлен на " + daysToIncrease + " дней";
        Long usersChatId = catAdoptionRepository.findById(idCat).get().getUsers().getChatId();
        if (usersChatId != null) {
            telegramBot.execute(new SendMessage(usersChatId, message));
        }
    }

}
