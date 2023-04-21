package com.example.javateambot.repository;

import com.example.javateambot.entity.AppPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<AppPhoto, Long> {
    AppPhoto findAppPhotoByChatId(long chatId);
}
