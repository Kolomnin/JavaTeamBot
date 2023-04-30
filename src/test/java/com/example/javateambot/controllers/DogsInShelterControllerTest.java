package com.example.javateambot.controllers;

import com.example.javateambot.entity.DogsInShelter;
import com.example.javateambot.service.DogsInShelterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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
@ExtendWith(MockitoExtension.class)
class DogsInShelterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads(){}

    @MockBean
    private DogsInShelterService dogsInShelterService;


        @Test
        void addAnimalInShelter() throws Exception{
            DogsInShelter animal = new DogsInShelter();
            animal.setIdDog(1);
            animal.setNameDog("Бобик");
            animal.setAge(3);

            Mockito.when(dogsInShelterService.addDogInShelter(Mockito.any())).thenReturn(animal);

            mockMvc.perform(
                            post("/dogsInShelter")
                                    .content(objectMapper.writeValueAsString(animal))
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(animal)));

        }

        @Test
        void editAnimalInShelter() throws Exception {
            DogsInShelter animal = new DogsInShelter();
            animal.setIdDog(1);
            animal.setNameDog("Бобик");
            animal.setAge(3);

            DogsInShelter newAnimal = new DogsInShelter();
            animal.setIdDog(1);
            animal.setNameDog("Шарик");
            animal.setAge(3);

            Mockito.when(dogsInShelterService.editDogInShelter(Mockito.any())).thenReturn(animal);

        mockMvc.perform(
                        put("/dogsInShelter")
                                .content(objectMapper.writeValueAsString(newAnimal))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDog").value(1))
                .andExpect(jsonPath("$.nameDog").value("Шарик"));
    }


    @Test
    void deleteAnimalInShelter() throws Exception{
        DogsInShelter animal = new DogsInShelter();
        animal.setIdDog(1);
        animal.setNameDog("Бобик");
        animal.setAge(3);

        Mockito.doNothing().when(dogsInShelterService).deleteDogInShelter(animal.getIdDog());

        mockMvc.perform(
                        delete("/dogsInShelter/{id}",1)
                                .content(objectMapper.writeValueAsString(animal))
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());
        Mockito.verify(dogsInShelterService, times(1)).deleteDogInShelter(animal.getIdDog());
    }

    @Test
    void findAnimalsInShelter() throws Exception{
        DogsInShelter animal = new DogsInShelter();
        animal.setIdDog(1);
        animal.setNameDog("Бобик");
        animal.setAge(3);

        List<DogsInShelter> animals = new LinkedList<>();
        animals.add(animal);

        Mockito.when(dogsInShelterService.findDogInShelterById(Mockito.anyLong())).thenReturn(animal);
        Mockito.when(dogsInShelterService.findDogInShelterByName(Mockito.anyString())).thenReturn(animal);
        Mockito.when(dogsInShelterService.getAllDogsInShelter()).thenReturn(animals);

        mockMvc.perform(
                        get("/dogsInShelter?id={id}",1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(animal)));

        mockMvc.perform(
                        get("/dogsInShelter?name=Бобик")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(animal)));

        mockMvc.perform(
                        get("/dogsInShelter"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(animals)));
    }
}

