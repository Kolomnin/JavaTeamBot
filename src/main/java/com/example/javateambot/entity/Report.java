package com.example.javateambot.entity;

import javax.persistence.*;


@Entity
@Table
public class Report {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long idReport;

    /**
     * Рацион питомца
     */
    @Column(nullable = false)
    private String ration;

    /**
     * Описание поведения питомца
     */
    @Column(nullable = false)
    private String animalBehavior;

    /**
     *Общее самочувствие и привыкание к новому месту
     */
    @Column(nullable = false)
    private String GeneralWellBeing;

    /**
     * Новые привычки
     */
    @Column(nullable = false)
    private String newHabits;

    @OneToOne
    private AppPhoto appPhoto;

//    @ManyToOne
//    @JoinColumn(name = "adoption_id")
//    private AnimalsInHouse animalsInHouse;


    public long getIdReport() {
        return idReport;
    }

    public void setIdReport(long idReport) {
        this.idReport = idReport;
    }

    public String getRation() {
        return ration;
    }

    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getAnimalBehavior() {
        return animalBehavior;
    }

    public void setAnimalBehavior(String animalBehavior) {
        this.animalBehavior = animalBehavior;
    }

    public String getGeneralWellBeing() {
        return GeneralWellBeing;
    }

    public void setGeneralWellBeing(String generalWellBeing) {
        GeneralWellBeing = generalWellBeing;
    }

    public String getNewHabits() {
        return newHabits;
    }

    public void setNewHabits(String newHabits) {
        this.newHabits = newHabits;
    }

    public AppPhoto getAppPhoto() {
        return appPhoto;
    }

    public void setAppPhoto(AppPhoto appPhoto) {
        this.appPhoto = appPhoto;
    }

    //    @Column(nullable = false)
//    private byte[] data;
//
//    @Column(nullable = false)
//    private String extension;



//    public String getExtension() {
//        return extension;
//    }
//
//    public void setExtension(String extension) {
//        this.extension = extension;
//    }
//
//
//    /**
//     * Метод позволяет получить байтовое представление фотографии
//     */
//    public byte[] getData() {
//        return data;
//    }
//
//    /**
//     * Метод позволяет сохранить байтовое представление фотографии
//     */
//    public void setData(byte[] data) {
//        this.data = data;
//    }


}
