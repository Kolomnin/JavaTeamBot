package com.example.javateambot.repository;

import com.example.javateambot.controllers.UsersController;
import com.example.javateambot.entity.AppPhoto;
import com.example.javateambot.entity.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<AppPhoto, Long> {
    //    AppPhoto findAppPhotoByChatIdAnd(long chatId);
    final Logger logger = LoggerFactory.getLogger(PhotoRepository.class);

    @Query("SELECT MAX(ap)  FROM AppPhoto ap WHERE ap.chatId = :chatId GROUP BY ap.chatId ")
    AppPhoto findLastReport1(long chatId);


}



