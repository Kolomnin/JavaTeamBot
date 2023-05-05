package com.example.javateambot.service;

import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.AdoptedDogs;
import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.DogAdoptionRepository;
import com.example.javateambot.repository.DogsInShelterRepository;
import com.example.javateambot.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class DogAdoptionService {
    Logger logger = LoggerFactory.getLogger(DogAdoptionService.class);

    private DogsInShelterRepository dogsInShelterRepository;


    private UsersRepository usersRepository;


    private DogAdoptionRepository dogAdoptionRepository;


    @Autowired
    public DogAdoptionService( DogsInShelterRepository dogsInShelterRepository, UsersRepository usersRepository, DogAdoptionRepository dogAdoptionRepository) {
        this.dogsInShelterRepository = dogsInShelterRepository;
        this.usersRepository = usersRepository;
        this.dogAdoptionRepository = dogAdoptionRepository;
    }



// работает с этим кодом, но в нем не красиво добавляется в свагере

    public AdoptedDogs adoptionDog2(Long userID, Long dogId, AdoptedDogs adoptedDogs) {

        if (dogsInShelterRepository.findById(dogId).isPresent()||usersRepository.findById(userID).isPresent()){
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

}
