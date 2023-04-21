package com.example.javateambot.controllers;

import com.example.javateambot.entity.AnimalsInShelter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AnimalsInShelterControllersUnitTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads(){}


    @Test
    void addAnimalsInShelter() throws Exception{
        AnimalsInShelter animal = new AnimalsInShelter();
        animal.setIdAnimal(1);
        animal.setNameAnimal("Бобик");
        animal.setAge(3);

        mockMvc.perform(
                        post("/animalsInShelter")
                                .content(objectMapper.writeValueAsString(animal))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(animal)));

    }

    @Test
    void editAnimalInShelter() {
    }

    @Test
    void deleteAnimalInShelter() {
    }

    @Test
    void findAnimalsInShelter() {
    }
}
