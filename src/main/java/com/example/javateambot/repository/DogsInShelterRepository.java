package com.example.javateambot.repository;


import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.DogsInShelter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogsInShelterRepository extends JpaRepository<DogsInShelter, Long> {

    final Logger logger = LoggerFactory.getLogger(DogsInShelterRepository.class);


    DogsInShelter findByNameDog(String name);

}
