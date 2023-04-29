package com.example.javateambot.repository;


import com.example.javateambot.entity.DogsInShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogsInShelterRepository extends JpaRepository<DogsInShelter, Long> {
    DogsInShelter findById(long id);

    DogsInShelter findByNameDog(String name);
}
