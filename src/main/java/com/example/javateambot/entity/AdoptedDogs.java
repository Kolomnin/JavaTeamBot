package com.example.javateambot.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Класс является сущностью объекта AdoptedDogs,
 * Это сущность животного ,которое будет храниться в базе данных
 */
@Entity
@Table(name = "adopted_dogs")
public class AdoptedDogs {

    /**
     * Id собаки
     * хранится в базе данных
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adoption;

    /**
     * Id собаки
     * хранится в базе данных
     */

    @Column(nullable = false)
    private long idDog;

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

//    @OneToMany(mappedBy = "animals_in_house")
//    private Collection<Report> report;

    /**
     * Дата когда был отправлен последний отчет о состоянии собаки,
     * который находится у хозаина,
     * хранится в базе данных
     */
    @Column(nullable = false)
    private LocalDate lastReportDate;


    /**
     * Метод позволяет получить Id собаки
     */
    public long getIdDog() {
        return idDog;
    }

    /**
     * Метод позволяет установить Id собаки
     */
    public void setIdDog(long idDog) {
        this.idDog = idDog;
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


}
