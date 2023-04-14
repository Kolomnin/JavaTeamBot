package com.example.javateambot.service;

import com.example.javateambot.entity.AppPhoto;
import com.example.javateambot.repository.PhotoRepository;
import com.example.javateambot.telegramExeption.UploadFileException;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.File;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.request.GetFile;
import com.pengrad.telegrambot.response.GetChatResponse;
import com.pengrad.telegrambot.response.GetFileResponse;
import okhttp3.internal.http2.Http2Stream;
import org.apache.catalina.connector.InputBuffer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


@Service
public class PhotoService {

    private TelegramBot telegramBot;
    private PhotoRepository photoRepository;

    public PhotoService(TelegramBot telegramBot,
                        PhotoRepository photoRepository) {
        this.telegramBot = telegramBot;
        this.photoRepository = photoRepository;
    }

    public void uploadPhoto(Message message) {
        PhotoSize telegramPhoto = message.photo()[0];
        GetFile request = new GetFile(telegramPhoto.fileId());
        GetFileResponse getFileResponse = telegramBot.execute(request);
        File file = getFileResponse.file();
        byte[] fileInByte = downloadFile(telegramBot.getFullFilePath(file));
        AppPhoto appPhoto = new AppPhoto();
        appPhoto.setFileSize(telegramPhoto.fileSize());
        appPhoto.setFileAsArrayOfBytes(fileInByte);
        photoRepository.save(appPhoto);
    }

    private byte[] downloadFile(String filePath) {
        System.out.println(filePath);
        URL urlObj = null;
        try {
            urlObj = new URL(filePath);
        } catch (MalformedURLException e) {
            throw new UploadFileException(e);
        }

        try (InputStream is = urlObj.openStream()) {
            return is.readAllBytes();
        } catch (IOException e) {
            throw new UploadFileException(urlObj.toExternalForm(), e);
        }
    }

}
