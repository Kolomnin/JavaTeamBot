package com.example.javateambot.entity;


import javax.persistence.*;

/**
 * Класс является сущностью объекта User,
 * Это сущность человека который взял из приюта питомца
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idUser;

    /**
     * Имя владельца питомца
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Фамилия владельца питомца
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Номер телефона владельца питомца
     */
    @Column(nullable = false)
    private String numberUser;


    /**
     * Метод для получения Id  владельца
     */
    public long getIdUser() {
        return idUser;
    }

    /**
     * Метод для установки  Id   владельца питомца
     */
    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    /**
     * Метод для получения имени владельца питомца
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Метод для записи,изменения имени владельца питомца
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Метод для получения имени владельца питомца
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Метод для записи, изменения имени владельца питомца
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Метод для получения, номера телефона владельца питомца
     */
    public String getNumberUser() {
        return numberUser;
    }

    /**
     * Метод для записи, изменеия номера телефона владельца питомца
     */
    public void setNumberUser(String numberUser) {
        this.numberUser = numberUser;
    }
}
