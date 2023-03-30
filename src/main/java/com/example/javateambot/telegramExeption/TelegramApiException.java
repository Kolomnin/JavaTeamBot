package com.example.javateambot.telegramExeption;

public class TelegramApiException extends RuntimeException {
    public TelegramApiException() {
    }

    public TelegramApiException(String message) {
        super(message);
    }

    public TelegramApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelegramApiException(Throwable cause) {
        super(cause);
    }

    public TelegramApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
