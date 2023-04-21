package com.example.javateambot.service;

import com.example.javateambot.entity.AnimalsInShelter;
import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.AnimalsInShelterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AnimalsInShelterServiceTest {

    private final AnimalsInShelterRepository animalsInShelterRepository = mock(AnimalsInShelterRepository.class);

    private AnimalsInShelterService out;

    @BeforeEach
    public void initOut() {
        out = new AnimalsInShelterService(animalsInShelterRepository);
    }

    @Test
    void addAnimalInShelter() {
        AnimalsInShelter animalsInShelter = new AnimalsInShelter();
        when(animalsInShelterRepository.save(animalsInShelter)).thenReturn(animalsInShelter);
        assertEquals(animalsInShelter, out.addAnimalInShelter(animalsInShelter));
    }

    @Test
    void editAnimalInShelter() {
        AnimalsInShelter animalsInShelter = new AnimalsInShelter();
        when(animalsInShelterRepository.save(animalsInShelter)).thenReturn(animalsInShelter);
        assertEquals(animalsInShelter, out.editAnimalInShelter(animalsInShelter));
    }

    @Test
    void getAllAnimalsInShelter() {
        AnimalsInShelter animalsInShelter1 = new AnimalsInShelter();
        AnimalsInShelter animalsInShelter2 = new AnimalsInShelter();
        AnimalsInShelter animalsInShelter3 = new AnimalsInShelter();
        List<AnimalsInShelter> exp = new ArrayList<>();
        exp.add(animalsInShelter1);
        exp.add(animalsInShelter2);
        exp.add(animalsInShelter3);
        when(animalsInShelterRepository.findAll()).thenReturn(exp);
        out.addAnimalInShelter(animalsInShelter1);
        out.addAnimalInShelter(animalsInShelter2);
        out.addAnimalInShelter(animalsInShelter3);
        assertEquals(exp, out.getAllAnimalsInShelter());
    }

    @Test
    void findAnimalInShelterById() {
        AnimalsInShelter animalsInShelter1 = new AnimalsInShelter();
        AnimalsInShelter animalsInShelter2 = new AnimalsInShelter();
        AnimalsInShelter animalsInShelter3 = new AnimalsInShelter();
        when(animalsInShelterRepository.findById(1)).thenReturn(animalsInShelter2);
        AnimalsInShelter exp = animalsInShelter2;
        assertEquals(exp, out.findAnimalInShelterById(1));
    }

    @Test
    void findAnimalInShelterByName() {
        AnimalsInShelter animalsInShelter1 = new AnimalsInShelter();
        AnimalsInShelter animalsInShelter2 = new AnimalsInShelter();
        AnimalsInShelter animalsInShelter3 = new AnimalsInShelter();
        animalsInShelter1.setNameAnimal("Шарик");
        animalsInShelter2.setNameAnimal("Мурзик");
        animalsInShelter3.setNameAnimal("Коржик");
        AnimalsInShelter exp = animalsInShelter2;
        when(animalsInShelterRepository.findByNameAnimal("Мурзик")).thenReturn(animalsInShelter2);
        assertEquals(exp, out.findAnimalInShelterByName("Мурзик"));
    }
}