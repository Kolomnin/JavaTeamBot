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

    public void saveReport(String report, String numberUser) {

        Long idUser = usersRepository.findByNumberUser(numberUser).getIdUser();
        animalsInHouseRepository.findByIdUser(idUser).setLastText(report);

    }

//    public Boolean checkNumberOwner(String number) {
//        if (usersRepository.findByNumberUser2(number)) {
//            return true ;
//        } else return false;
//    }



}
