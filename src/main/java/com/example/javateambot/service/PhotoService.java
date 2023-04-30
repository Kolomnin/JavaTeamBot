package com.example.javateambot.service;

import com.example.javateambot.entity.AppPhoto;
import com.example.javateambot.repository.PhotoRepository;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.response.GetFileResponse;


import liquibase.util.StringUtil;
import okhttp3.internal.http2.Http2Stream;
import org.apache.catalina.connector.InputBuffer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * сервис работы с фотографиями отчета
 *
 * @author Северин
 */
@Service
public class PhotoService {

    private TelegramBot telegramBot;
    private PhotoRepository photoRepository;
    public PhotoService(TelegramBot telegramBot,
                        PhotoRepository photoRepository) {
        this.telegramBot = telegramBot;
        this.photoRepository = photoRepository;
    }


    public AppPhoto findPhotoLastId(long chatId) {
        return photoRepository.findLastReport1(chatId);
    }

    /**
     * Сохранение фотографии в базе данных
     * Используетcя методы репозитория{@link}
     *
     * @param message
     */
    public void uploadPhoto(Message message) {
        PhotoSize[] photoSizes = message.photo();
        PhotoSize photoSize = photoSizes[photoSizes.length - 1];
        GetFileResponse getFileResponse = telegramBot.execute(new GetFile(photoSize.fileId()));
        AppPhoto photo = new AppPhoto();
        if (getFileResponse.isOk()) {
            String extension = StringUtils.getFilenameExtension(getFileResponse.file().filePath());
            photo.setExtension(extension);
            try {
                byte[] image = telegramBot.getFileContent(getFileResponse.file());
                photo.setData(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        photo.setChatId(message.chat().id());
        photoRepository.save(photo);
    }
}
