package com.example.javateambot.repository;


import com.example.javateambot.entity.CatsInShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatsInShelterRepository extends JpaRepository<CatsInShelter, Long> {
    CatsInShelter findById(long id);

    CatsInShelter findByNameCat(String name);
}
