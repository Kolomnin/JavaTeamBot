package com.example.javateambot.controllers;

import com.example.javateambot.entity.AnimalsInShelter;
import com.example.javateambot.service.AnimalsInShelterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
class AnimalsInShelterControllersUnitTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads(){}

    @MockBean
    private AnimalsInShelterService animalsInShelterService;


        @Test
        void addAnimalInShelter() throws Exception{
            AnimalsInShelter animal = new AnimalsInShelter();
            animal.setIdAnimal(1);
            animal.setNameAnimal("Бобик");
            animal.setAge(3);

            Mockito.when(animalsInShelterService.addAnimalInShelter(Mockito.any())).thenReturn(animal);

            mockMvc.perform(
                            post("/animalsInShelter")
                                    .content(objectMapper.writeValueAsString(animal))
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(animal)));

        }

        @Test
        void editAnimalInShelter() throws Exception {
            AnimalsInShelter animal = new AnimalsInShelter();
            animal.setIdAnimal(1);
            animal.setNameAnimal("Бобик");
            animal.setAge(3);

            AnimalsInShelter newAnimal = new AnimalsInShelter();
            animal.setIdAnimal(1);
            animal.setNameAnimal("Шарик");
            animal.setAge(3);

            Mockito.when(animalsInShelterService.editAnimalInShelter(Mockito.any())).thenReturn(animal);

        mockMvc.perform(
                        put("/animalsInShelter")
                                .content(objectMapper.writeValueAsString(newAnimal))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idAnimal").value(1))
                .andExpect(jsonPath("$.nameAnimal").value("Шарик"));
    }


    @Test
    void deleteAnimalInShelter() throws Exception{
        AnimalsInShelter animal = new AnimalsInShelter();
        animal.setIdAnimal(1);
        animal.setNameAnimal("Бобик");
        animal.setAge(3);

        Mockito.doNothing().when(animalsInShelterService).deleteAnimalInShelter(animal.getIdAnimal());

        mockMvc.perform(
                        delete("/animalsInShelter/{id}",1)
                                .content(objectMapper.writeValueAsString(animal))
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());
        Mockito.verify(animalsInShelterService, times(1)).deleteAnimalInShelter(animal.getIdAnimal());
    }

    @Test
    void findAnimalsInShelter() throws Exception{
        AnimalsInShelter animal = new AnimalsInShelter();
        animal.setIdAnimal(1);
        animal.setNameAnimal("Бобик");
        animal.setAge(3);

        List<AnimalsInShelter> animals = new LinkedList<>();
        animals.add(animal);

        Mockito.when(animalsInShelterService.findAnimalInShelterById(Mockito.anyInt())).thenReturn(animal);
        Mockito.when(animalsInShelterService.findAnimalInShelterByName(Mockito.anyString())).thenReturn(animal);
        Mockito.when(animalsInShelterService.getAllAnimalsInShelter()).thenReturn(animals);

        mockMvc.perform(
                        get("/animalsInShelter")
                                .content(objectMapper.writeValueAsString(1))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(animal)));

        mockMvc.perform(
                        get("/animalsInShelter")
                                .content(objectMapper.writeValueAsString("Бобик"))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(animal)));

        mockMvc.perform(
                        get("/animalsInShelter"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(animals)));
    }
}

