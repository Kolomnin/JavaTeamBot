package com.example.javateambot.service;

import com.example.javateambot.entity.AppPhoto;
import com.example.javateambot.repository.PhotoRepository;
import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhotoServiceTest {

    private final PhotoRepository photoRepository = mock(PhotoRepository.class);
    private final TelegramBot telegramBot = mock(TelegramBot.class);

    private PhotoService out;

    @Test
    void findPhotoLastId() {
        out = new PhotoService(telegramBot, photoRepository);
        AppPhoto appPhoto = new AppPhoto();
        appPhoto.setChatId(1L);
        when(photoRepository.findAppPhotoByChatId(1)).thenReturn(appPhoto);
        assertEquals(appPhoto, out.findPhotoLastId(1));
    }
}