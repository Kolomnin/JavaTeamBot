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

    @OneToOne
    private DogsInShelter dogs;

    /**
     * Id владельца
     * хранится в базе данных
     */
    @OneToOne
    private Users users;

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

    @Column(nullable = false)
    private LocalDate adoptionDate;


    /**
     * Метод позволяет получить Id собаки
     */
    public DogsInShelter getDogs() {
        return dogs;
    }

    /**
     * Метод позволяет установить Id собаки
     */
    public void setDogs(DogsInShelter dogs) {
        this.dogs = dogs;
    }

    /**
     * Метод позволяет получить ID владельца
     */
    public Users getUsers() {
        return users;
    }

    /**
     * Метод позволяет установить ID владельца
     */
    public void setUsers(Users users) {
        this.users = users;
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
     * Метод позволяет получить дату когда было усыновлено животное
     */
    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }
    /**
     * Метод позволяет установить дату когда было усыновлено животное
     */
    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate = adoptionDate;
    }


}
