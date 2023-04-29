package com.example.javateambot.entity;


import javax.persistence.*;

/**
 * Класс является сущностью объекта DogsShelter,
 * Это сущность животного ,которое будет храниться в базе данных
 */
@Entity
@Table(name = "dogs_in_shelter")
public class DogsInShelter {

    /**
     * Id собаки
     * хранится в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDog;

    /**
     * Имя собаки
     * хранится в базе данных
     */
    @Column(nullable = false)
    private String nameDog;

    /**
     * Возраст собаки
     * хранится в базе данных
     */
    @Column(nullable = false)
    private int age;

    /**
     * Наличие у собаки ограниченных возможностей
     * хранится в базе данных
     */
    @Column(nullable = false)
    private boolean disabilities;

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
     * Метод позволяет получить имя собаки
     */
    public String getNameDog() {
        return nameDog;
    }
    /**
     * Метод позволяет установить имя собаки
     */
    public void setNameDog(String nameDog) {
        this.nameDog = nameDog;
    }
    /**
     * Метод позволяет получить возраст собаки
     */
    public int getAge() {
        return age;
    }
    /**
     * Метод позволяет установить возраст собаки
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Метод позволяет получить наличие у собаки ограниченных возможностей
     */
    public boolean isDisabilities() {
        return disabilities;
    }

    /**
     * Метод позволяет установить наличие у собаки ограниченных возможностей
     */
    public void setDisabilities(boolean disabilities) {
        this.disabilities = disabilities;
    }
}
