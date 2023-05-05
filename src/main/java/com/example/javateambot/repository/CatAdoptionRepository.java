package com.example.javateambot.repository;

import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.AdoptedCats;
import com.example.javateambot.entity.AdoptedDogs;
import com.example.javateambot.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CatAdoptionRepository extends JpaRepository<AdoptedCats, Long> {
        Logger logger = LoggerFactory.getLogger(CatAdoptionRepository.class);

        Long findByUsersChatId (Users users);
        AdoptedCats findByLastDateProbationPeriod (LocalDate localDate);
//        @Query("SELECT e FROM AdoptedCats e WHERE e.lastDateProbationPeriod = :date")
//        List<AdoptedCats> findByDate(@Param("date") LocalDate date);

        AdoptedCats findById(long idCat);

}