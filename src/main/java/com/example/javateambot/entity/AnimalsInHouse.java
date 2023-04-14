package com.example.javateambot.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Класс является сущностью объекта AnimalsInHouse,
 * Это сущность животного ,которое будет храниться в базе данных
 */
@Entity
@Table(name = "animalsInHouse")
public class AnimalsInHouse {

    /**
     * Id животного
     * хранится в базе данных
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adoption;

    @Column(nullable = false)
    private long idAnimal;

    /**
     * Id владельца
     * хранится в базе данных
     */
    @Column(nullable = false)
    private long idUser;

    /**
     * Дата конца испытательного срока у хозяина,
     * хранится в базе данных
     */
    @Column(nullable = false)
    private LocalDate lastDateProbationPeriod;

    /**
     * Дата когда был отправлен последний отчет о состоянии животного,
     * который находится у хозаина,
     * хранится в базе данных
     */
    @Column(nullable = false)
    private LocalDate lastReportDate;


    /**
     * Текст отчета о состоянии питомца, который отправляется ежедневно
     */
    @Column(nullable = false)
    private String lastText;


    /**
     * Метод позволяет получить Id животного
     */
    public long getIdAnimal() {
        return idAnimal;
    }

    /**
     * Метод позволяет установить Id животного
     */
    public void setIdAnimal(long idAnimal) {
        this.idAnimal = idAnimal;
    }

    /**
     * Метод позволяет получить ID владельца
     */
    public long getIdUser() {
        return idUser;
    }

    /**
     * Метод позволяет установить ID владельца
     */
    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    /**
     * Метод позволяет получить дату  последнего дня испытательного срока
     */
    public LocalDate getLastDateProbationPeriod() {
        return lastDateProbationPeriod;
    }

    /**
     * Метод позволяет установить дату последнего дня испытательного срока
     */
    public void setLastDateProbationPeriod(LocalDate lastDateProbationPeriod) {
        this.lastDateProbationPeriod = lastDateProbationPeriod;
    }

    /**
     * Метод позволяет получить дату когда был отправлен последний отчет
     */
    public LocalDate getLastReportDate() {
        return lastReportDate;
    }

    /**
     * Метод позволяет установить дату когда был отправлен последний отчет
     */
    public void setLastReportDate(LocalDate lastReportDate) {
        this.lastReportDate = lastReportDate;
    }


    /**
     * Метод позволяет получить текст отчета
     */
    public String getLastText() {
        return lastText;
    }

    /**
     * Метод позволяет установить текст отчета
     */
    public void setLastText(String lastText) {
        this.lastText = lastText;
    }

}
