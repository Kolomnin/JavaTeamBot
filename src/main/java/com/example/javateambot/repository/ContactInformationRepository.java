package com.example.javateambot.repository;

import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.ContactInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformation,Long> {

    final Logger logger = LoggerFactory.getLogger(ContactInformationRepository.class);

}
