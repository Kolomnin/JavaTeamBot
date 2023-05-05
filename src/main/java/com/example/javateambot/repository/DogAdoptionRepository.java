package com.example.javateambot.repository;

import com.example.javateambot.entity.AdoptedDogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogAdoptionRepository extends JpaRepository<AdoptedDogs, Long> {

    AdoptedDogs findById(long idDog);

}
