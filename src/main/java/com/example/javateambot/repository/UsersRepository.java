package com.example.javateambot.repository;


import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

    final Logger logger = LoggerFactory.getLogger(UsersRepository.class);

    Users findById(long id);

    Users findByNumberUser(String numberUser);

    Users findByChatId (Long chatId);

    @Query(value = "SELECT users FROM Users users INNER JOIN Report report ON users.idUser=report.user.idUser WHERE report.date < :date")
    List<Users> findUsersByDate(@Param("date") LocalDate date);

//    Boolean findByNumberUseraOrderBy (String number);
}

