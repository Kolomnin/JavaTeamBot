package com.example.javateambot.repository;

import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.AdoptedDogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogAdoptionRepository extends JpaRepository<AdoptedDogs, Long> {


    AdoptedDogs findById(long idDog);


    final Logger logger = LoggerFactory.getLogger(DogAdoptionRepository.class);

}
