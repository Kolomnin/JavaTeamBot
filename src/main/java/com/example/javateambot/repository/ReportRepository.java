package com.example.javateambot.repository;


import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.Report;

import com.example.javateambot.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report , Long> {


    final Logger logger = LoggerFactory.getLogger(ReportRepository.class);

    List<Report> findByDateAfterAndUser (LocalDate localDate,Users users);

    List<Report> findByDateAfter (LocalDate localDate);

}
