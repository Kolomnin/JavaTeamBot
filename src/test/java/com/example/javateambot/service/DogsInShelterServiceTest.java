package com.example.javateambot.service;

import com.example.javateambot.entity.DogsInShelter;
import com.example.javateambot.repository.DogsInShelterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogsInShelterServiceTest {

    private final DogsInShelterRepository dogsInShelterRepository = mock(DogsInShelterRepository.class);

    private DogsInShelterService out;

    @BeforeEach
    public void initOut() {
        out = new DogsInShelterService(dogsInShelterRepository);
    }

    @Test
    void addAnimalInShelter() {
        DogsInShelter dogsInShelter = new DogsInShelter();
        when(dogsInShelterRepository.save(dogsInShelter)).thenReturn(dogsInShelter);
        assertEquals(dogsInShelter, out.addDogInShelter(dogsInShelter));
    }

    @Test
    void editAnimalInShelter() {
        DogsInShelter dogsInShelter = new DogsInShelter();
        when(dogsInShelterRepository.save(dogsInShelter)).thenReturn(dogsInShelter);
        assertEquals(dogsInShelter, out.editDogInShelter(dogsInShelter));
    }

    @Test
    void getAllAnimalsInShelter() {
        DogsInShelter dogsInShelter1 = new DogsInShelter();
        DogsInShelter dogsInShelter2 = new DogsInShelter();
        DogsInShelter dogsInShelter3 = new DogsInShelter();
        List<DogsInShelter> exp = new ArrayList<>();
        exp.add(dogsInShelter1);
        exp.add(dogsInShelter2);
        exp.add(dogsInShelter3);
        when(dogsInShelterRepository.findAll()).thenReturn(exp);
        out.addDogInShelter(dogsInShelter1);
        out.addDogInShelter(dogsInShelter2);
        out.addDogInShelter(dogsInShelter3);
        assertEquals(exp, out.getAllDogsInShelter());
    }

    @Test
    void findAnimalInShelterById() {
        DogsInShelter dogsInShelter1 = new DogsInShelter();
        DogsInShelter dogsInShelter2 = new DogsInShelter();
        DogsInShelter dogsInShelter3 = new DogsInShelter();
        when(dogsInShelterRepository.findById(1)).thenReturn(dogsInShelter2);
        DogsInShelter exp = dogsInShelter2;
        assertEquals(exp, out.findDogInShelterById(1));
    }

    @Test
    void findAnimalInShelterByName() {
        DogsInShelter dogsInShelter1 = new DogsInShelter();
        DogsInShelter dogsInShelter2 = new DogsInShelter();
        DogsInShelter dogsInShelter3 = new DogsInShelter();
        dogsInShelter1.setNameDog("Шарик");
        dogsInShelter2.setNameDog("Мурзик");
        dogsInShelter3.setNameDog("Коржик");
        DogsInShelter exp = dogsInShelter2;
        when(dogsInShelterRepository.findByNameDog("Мурзик")).thenReturn(dogsInShelter2);
        assertEquals(exp, out.findDogInShelterByName("Мурзик"));
    }
}