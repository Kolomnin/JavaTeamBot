package com.example.javateambot.repository;

import com.example.javateambot.entity.AdoptedCats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatAdoptionRepository extends JpaRepository<AdoptedCats, Long> {

}