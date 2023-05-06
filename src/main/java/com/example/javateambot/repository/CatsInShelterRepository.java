package com.example.javateambot.repository;


import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.CatsInShelter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatsInShelterRepository extends JpaRepository<CatsInShelter, Long> {
     final Logger logger = LoggerFactory.getLogger(CatsInShelterRepository.class);

    CatsInShelter findByNameCat(String name);
}
