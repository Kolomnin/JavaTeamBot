package com.example.javateambot.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Класс является сущностью объекта AdoptedCats,
 * Это сущность животного ,которое будет храниться в базе данных
 */
@Entity
@Table(name = "adopted_cats")
public class AdoptedCats {

    /**
     * Id кошки
     * хранится в базе данных
     */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adoption;

    /**
     * Id кошки
     * хранится в базе данных
     */

//    @Column(nullable = false)

    @OneToOne
    private CatsInShelter cats;

    /**
     * Id владельца
     * хранится в базе данных
     */
//    @Column(nullable = false)
    @OneToOne
    private Users users;

    /**
     * Дата конца испытательного срока у хозяина,
     * хранится в базе данных
     */
    @Column(nullable = false)
    private LocalDate lastDateProbationPeriod;


    @Column(nullable = false)
    private LocalDate adoptionDate;

//    @OneToMany(mappedBy = "animals_in_house")
//    private Collection<Report> report;

    /**
     * Дата когда был отправлен последний отчет о состоянии кошки,
     * который находится у хозаина,
     * хранится в базе данных
     */
    @Column(nullable = false)
    private LocalDate lastReportDate;


    /**
     * Метод позволяет получить Id кошки
     */
    public CatsInShelter getCats() {
        return cats;
    }

    /**
     * Метод позволяет установить Id кошки
     */
    public void setCats(CatsInShelter cats) {
        this.cats = cats;
    }

    /**
     * Метод позволяет получить ID владельца
     */
//    public long getUsers() {
//        return idUser;
//    }


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
