package com.example.javateambot.service;

import com.example.javateambot.entity.AnimalsInHouse;
import com.example.javateambot.entity.AnimalsInShelter;
import com.example.javateambot.repository.AnimalsInHouseRepository;
import com.example.javateambot.repository.AnimalsInShelterRepository;
import com.example.javateambot.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class DogAdoptionService {


    public DogAdoptionService(AnimalsInHouseRepository animalsInHouseRepository) {
        this.animalsInHouseRepository = animalsInHouseRepository;
    }


    AnimalsInHouse animalsInHouse;

    @Autowired
    AnimalsInShelterRepository animalsInShelterRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AnimalsInHouseRepository animalsInHouseRepository;


// работает с этим кодом но в нем не красиво доавляется в свагере

    public AnimalsInHouse adoptionDog2(Long userID,Long animalId,AnimalsInHouse animalsInHouse) {

        if (animalsInShelterRepository.findById(animalId).isPresent()||usersRepository.findById(userID).isPresent()){
            animalsInHouse.setIdUser(userID);
            animalsInHouse.setIdAnimal(animalId);
            animalsInHouse.setLastDateProbationPeriod(LocalDate.now().plusDays(30));

        }

        return animalsInHouseRepository.save(animalsInHouse);
    }

//    public void editAnimalInShelter(AnimalsInHouse animal,String report) {
//        animal =         animalsInHouseRepository.findByIdUser(getId("222"));
//
//        animal.setLastText(report);
//        animalsInHouseRepository.save(animal);
//    }
//    public Long getId(String number) {
//        Long idUser = usersRepository.findByNumberUser(number).getIdUser();
//
//        return idUser;
//    }

    public Boolean checkChatId(Long chatId) {
        if (Objects.equals(usersRepository.findByChatId(chatId).getChatId(), chatId)) {
            return true ;
        } else return false;
    }

}
