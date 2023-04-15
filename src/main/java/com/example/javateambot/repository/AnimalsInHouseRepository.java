package com.example.javateambot.repository;

import com.example.javateambot.entity.AnimalsInHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalsInHouseRepository extends JpaRepository<AnimalsInHouse, Long> {
    AnimalsInHouse findByIdUser (long Id);
}
