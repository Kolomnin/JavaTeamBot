package com.example.javateambot.repository;

import com.example.javateambot.entity.AnimalsInHouse;
import com.example.javateambot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnimalsInHouseRepository extends JpaRepository<AnimalsInHouse, Long> {
    AnimalsInHouse findByIdUser (long Id);

    List<Users> findAllByLastReportDateGreaterThan(LocalDate localDate);
}
