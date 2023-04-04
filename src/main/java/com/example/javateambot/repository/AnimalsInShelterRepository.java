package com.example.javateambot.repository;


import com.example.javateambot.entity.AnimalsInShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalsInShelterRepository extends JpaRepository<AnimalsInShelter, Long> {
    AnimalsInShelter findById(long id);

    AnimalsInShelter findByNameAnimal(String name);
}
