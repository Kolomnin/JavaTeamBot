package com.example.javateambot.entity;



import javax.persistence.*;

/**
 * Класс является сущностью объекта CatsShelter,
 * Это сущность животного ,которое будет храниться в базе данных
 */
@Entity
@Table(name = "cats_in_shelter")
public class CatsInShelter {

    /**
     * Id кошки
     * хранится в базе данных
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCat;

    /**
     * Имя кошки
     * хранится в базе данных
     */
    @Column(nullable = false)
    private String nameCat;

    /**
     * Возраст кошки
     * хранится в базе данных
     */
    @Column(nullable = false)
    private int age;

    /**
     * Наличие у кошки ограниченных возможностей
     * хранится в базе данных
     */
    @Column(nullable = false)
    private boolean disabilities;

    /**
     * Метод позволяет получить Id кошки
     */
    public long getIdCat() {
        return idCat;
    }
    /**
     * Метод позволяет установить Id кошки
     */
    public void setIdCat(long idCat) {
        this.idCat = idCat;
    }
    /**
     * Метод позволяет получить имя кошки
     */
    public String getNameCat() {
        return nameCat;
    }
    /**
     * Метод позволяет установить имя кошки
     */
    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }
    /**
     * Метод позволяет получить возраст кошки
     */
    public int getAge() {
        return age;
    }
    /**
     * Метод позволяет установить возраст кошки
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Метод позволяет получить наличие у кошки ограниченных возможностей
     */
    public boolean isDisabilities() {
        return disabilities;
    }

    /**
     * Метод позволяет установить наличие у кошки ограниченных возможностей
     */
    public void setDisabilities(boolean disabilities) {
        this.disabilities = disabilities;
    }
}
