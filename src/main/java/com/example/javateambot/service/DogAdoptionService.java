package com.example.javateambot.service;

import com.example.javateambot.entity.AnimalsInHouse;
import com.example.javateambot.entity.AnimalsInShelter;
import com.example.javateambot.repository.AnimalsInHouseRepository;
import com.example.javateambot.repository.AnimalsInShelterRepository;
import com.example.javateambot.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DogAdoptionService {


    public DogAdoptionService(AnimalsInHouseRepository animalsInHouseRepository) {
        this.animalsInHouseRepository = animalsInHouseRepository;
    }


    AnimalsInHouse animalsInHouse;

    @Autowired
    AnimalsInShelterRepository animalsInShelterRepository;

    UsersRepository usersRepository;


    AnimalsInHouseRepository animalsInHouseRepository;

//    public AnimalsInHouse adoptionDog(Long userID,Long animalId) {
//
//
//            animalsInHouse.setIdUser(userID);
//            animalsInHouse.setIdAnimal(animalId);
//
//
//        return    animalsInHouseRepository.save(animalsInHouse);
//
//
//
//
//
//    }

// работает с этим кодом но в нем не красиво доавляется в свагере

    public AnimalsInHouse adoptionDog2(Long userID,Long animalId,AnimalsInHouse animalsInHouse) {

        if (animalsInShelterRepository.findById(animalId).isPresent()||usersRepository.findById(userID).isPresent()){
            animalsInHouse.setIdUser(userID);
            animalsInHouse.setIdAnimal(animalId);
            animalsInHouse.setLastDateProbationPeriod(LocalDate.now().plusDays(30));

        }

        return animalsInHouseRepository.save(animalsInHouse);
    }



}
