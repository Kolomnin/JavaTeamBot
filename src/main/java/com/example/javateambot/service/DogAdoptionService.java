package com.example.javateambot.service;

import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.AdoptedDogs;

import com.example.javateambot.repository.*;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.DogAdoptionRepository;
import com.example.javateambot.repository.DogsInShelterRepository;
import com.example.javateambot.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class DogAdoptionService {
    Logger logger = LoggerFactory.getLogger(DogAdoptionService.class);

    private DogsInShelterRepository dogsInShelterRepository;

    private UsersRepository usersRepository;

    private DogAdoptionRepository dogAdoptionRepository;

    private TelegramBot telegramBot;

    @Autowired
    public DogAdoptionService(DogsInShelterRepository dogsInShelterRepository, UsersRepository usersRepository, DogAdoptionRepository dogAdoptionRepository, TelegramBot telegramBot) {
        this.dogsInShelterRepository = dogsInShelterRepository;
        this.usersRepository = usersRepository;
        this.dogAdoptionRepository = dogAdoptionRepository;
        this.telegramBot = telegramBot;
    }


// работает с этим кодом, но в нем не красиво добавляется в свагере
    public AdoptedDogs adoptionDog2(Long userID, Long dogId, AdoptedDogs adoptedDogs) {

        if (dogsInShelterRepository.findById(dogId).isPresent() || usersRepository.findById(userID).isPresent()) {
            adoptedDogs.setUsers(usersRepository.findById(userID).orElseThrow());
            adoptedDogs.setDogs(dogsInShelterRepository.findById(dogId).orElseThrow());
            adoptedDogs.setLastDateProbationPeriod(LocalDate.now().plusDays(30));
            adoptedDogs.setAdoptionDate(LocalDate.now());

        }

        return dogAdoptionRepository.save(adoptedDogs);
    }

//    public void editAnimalInShelter(AnimalsInHouse animal,String report) {
//        animal =         animalsInHouseRepository.findByIdUser(getId("222"));
//
//        animal.setLastText(report);
//        animalsInHouseRepository.save(animal);
//    }
//    public Long getId(String number) {
//        Long idUser = usersRepository.findByNumberUser(number).getUsers();
//
//        return idUser;
//    }

ExtensionProbationPeriod
    public Boolean checkChatId(Long chatId) {
        if (Objects.equals(usersRepository.findByChatId(chatId).getChatId(), chatId)) {
            return true;
        } else return false;
    }

    public void increaseProbationPeriodDog(Long idDog, int daysToIncrease) {
        //если указано дней меньше 14, то увеличение будет на 14, иначе - на 30 дней
        if (daysToIncrease <= 14) {
            daysToIncrease = 14;
        } else {
            daysToIncrease = 30;
        }

        //увеличиваем дату
        AdoptedDogs dog = dogAdoptionRepository.findById(idDog).orElseThrow();
        LocalDate newDate = dog.getLastDateProbationPeriod().plusDays(daysToIncrease);
        dog.setLastDateProbationPeriod(newDate);
        dogAdoptionRepository.save(dog);

        //сообщаем пользователю об этом
        String message = "Ваш испытательный срок был продлен на " + daysToIncrease + " дней";
        Long usersChatId = dogAdoptionRepository.findById(idDog).get().getUsers().getChatId();
        if (usersChatId != null) {
            telegramBot.execute(new SendMessage(usersChatId, message));
        }
    }

}
