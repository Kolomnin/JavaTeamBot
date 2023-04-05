package com.example.javateambot.entity;


import javax.persistence.*;

/**
 * Класс является сущностью объекта AnimalsShelter,
 * Это сущность животного ,которое будет храниться в базе данных
 */
@Entity
@Table(name = "animalsInShelter")
public class AnimalsInShelter {

    /**
     * Id животного
     * хранится в базе данных
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAnimal;

    /**
     * Имя животного
     * хранится в базе данных
     */
    @Column(nullable = false)
    private String nameAnimal;

    /**
     * Возраст животного
     * хранится в базе данных
     */
    @Column(nullable = false)
    private int age;

    /**
     * аличие у животного ограниченных возможностей
     * хранится в базе данных
     */
    @Column(nullable = false)
    private boolean disabilities;

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
     * Метод позволяет поулчить имя животного
     */
    public String getNameAnimal() {
        return nameAnimal;
    }
    /**
     * Метод позволяет установить имя животного
     */
    public void setNameAnimal(String nameAnimal) {
        this.nameAnimal = nameAnimal;
    }
    /**
     * Метод позволяет получить возраст животного
     */
    public int getAge() {
        return age;
    }
    /**
     * Метод позволяет установить возраст животного
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Метод позволяет получить наличие у животного ограниченных возможностей
     */
    public boolean isDisabilities() {
        return disabilities;
    }

    /**
     * Метод позволяет установить наличие у животного ограниченных возможностей
     */
    public void setDisabilities(boolean disabilities) {
        this.disabilities = disabilities;
    }
}
