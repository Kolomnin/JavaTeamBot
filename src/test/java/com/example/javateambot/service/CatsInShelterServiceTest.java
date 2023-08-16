package com.example.javateambot.service;

import com.example.javateambot.entity.CatsInShelter;
import com.example.javateambot.repository.CatsInShelterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatsInShelterServiceTest {


        @Mock
        private CatsInShelterRepository catsInShelterRepository;

        @InjectMocks
        private CatsInShelterService catsInShelterService;

        @Test
        public void addCatInShelterTest() {
            CatsInShelter cat = new CatsInShelter();
            cat.setIdCat(1);
            cat.setNameCat("murzik");
            cat.setAge(2);
            when(catsInShelterRepository.save(cat)).thenReturn(cat);
            CatsInShelter result = catsInShelterService.addCatInShelter(cat);
            assertEquals(cat, result);
        }
//
        @Test
        public void editCatInShelterTest() {
            CatsInShelter cat = new CatsInShelter();
            cat.setIdCat(1);
            cat.setNameCat("murzik");
            cat.setAge(2);
            when(catsInShelterRepository.save(cat)).thenReturn(cat);
            CatsInShelter savedCat = catsInShelterService.addCatInShelter(cat);
            savedCat.setNameCat("Snowball");
            when(catsInShelterRepository.save(savedCat)).thenReturn(savedCat);
            CatsInShelter updatedCat = catsInShelterService.editCatInShelter(savedCat);
            assertEquals(savedCat, updatedCat);
        }
//
        @Test
        public void deleteCatInShelterTest() {
            CatsInShelter cat = new CatsInShelter();
            cat.setIdCat(1);
            cat.setNameCat("murzik");
            cat.setAge(2);
            when(catsInShelterRepository.save(cat)).thenReturn(cat);
            CatsInShelter savedCat = catsInShelterService.addCatInShelter(cat);
            doNothing().when(catsInShelterRepository).deleteById(savedCat.getIdCat());
            catsInShelterService.deleteCatInShelter(savedCat.getIdCat());
            verify(catsInShelterRepository, times(1)).deleteById(savedCat.getIdCat());
        }
//
        @Test
        public void getAllCatsInShelterTest() {
            List<CatsInShelter> cats = new ArrayList<>();
            CatsInShelter cat = new CatsInShelter();
            cat.setIdCat(1);
            cat.setNameCat("murzik");
            cat.setAge(2);

            CatsInShelter cat2 = new CatsInShelter();
            cat.setIdCat(2);
            cat.setNameCat("belash");
            cat.setAge(3);

            when(catsInShelterRepository.findAll()).thenReturn(cats);
            Collection<CatsInShelter> result = catsInShelterService.getAllCatsInShelter();
            assertEquals(cats, result);
        }
//
        @Test
        public void findCatInShelterByIdTest() {
            CatsInShelter cat = new CatsInShelter();
            cat.setIdCat(1);
            cat.setNameCat("murzik");
            cat.setAge(2);
            when(catsInShelterRepository.findById(cat.getIdCat())).thenReturn(Optional.of(cat));
            CatsInShelter result = catsInShelterService.findCatInShelterById(cat.getIdCat());
            assertEquals(cat, result);
        }

        @Test
        public void findCatInShelterByNameTest() {
            CatsInShelter cat = new CatsInShelter();
            cat.setIdCat(1);
            cat.setNameCat("murzik");
            cat.setAge(2);
            when(catsInShelterRepository.findByNameCat(cat.getNameCat())).thenReturn(cat);
            CatsInShelter result = catsInShelterService.findCatInShelterByName(cat.getNameCat());
            assertEquals(cat, result);
        }
    }
