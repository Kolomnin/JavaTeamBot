package com.example.javateambot.controllers;

import com.example.javateambot.entity.CatsInShelter;
import com.example.javateambot.entity.DogsInShelter;
import com.example.javateambot.service.CatsInShelterService;
import com.example.javateambot.service.DogsInShelterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class CatsInShelterControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads(){}

    @MockBean
    private CatsInShelterService catsInShelterService;


    @Test
    void addAnimalInShelter() throws Exception{
        CatsInShelter cats = new CatsInShelter();
        cats.setIdCat(1);
        cats.setNameCat("Мурзик");
        cats.setAge(3);
        cats.setDisabilities(false);

        Mockito.when(catsInShelterService.addCatInShelter(Mockito.any())).thenReturn(cats);

        mockMvc.perform(
                        post("/catsInShelter")
                                .content(objectMapper.writeValueAsString(cats))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cats)));

    }

    @Test
    void editAnimalInShelter() throws Exception {
        CatsInShelter cats = new CatsInShelter();
        cats.setIdCat(1);
        cats.setNameCat("Мурзик");
        cats.setAge(3);

        CatsInShelter cats2 = new CatsInShelter();
        cats.setIdCat(1);
        cats.setNameCat("биба");
        cats.setAge(3);

        Mockito.when(catsInShelterService.editCatInShelter(Mockito.any())).thenReturn(cats);

        mockMvc.perform(
                        put("/catsInShelter")
                                .content(objectMapper.writeValueAsString(cats2))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCat").value(1))
                .andExpect(jsonPath("$.nameCat").value("биба"));
    }
//
//
    @Test
    void deleteAnimalInShelter() throws Exception{
        DogsInShelter animal = new DogsInShelter();
        animal.setIdDog(1);
        animal.setNameDog("Бобик");
        animal.setAge(3);

        Mockito.doNothing().when(catsInShelterService).deleteCatInShelter(animal.getIdDog());

        mockMvc.perform(
                        delete("/catsInShelter/{id}",1)
                                .content(objectMapper.writeValueAsString(animal))
                                .contentType(MediaType.APPLICATION_JSON)

                )
                .andExpect(status().isOk());
        Mockito.verify(catsInShelterService, times(1)).deleteCatInShelter(animal.getIdDog());
    }
//
    @Test
    void findAnimalsInShelter() throws Exception{
        CatsInShelter cats = new CatsInShelter();
        cats.setIdCat(1);
        cats.setNameCat("Бобик");
        cats.setAge(3);

        List<CatsInShelter> animals = new LinkedList<>();
        animals.add(cats);

        Mockito.when(catsInShelterService.findCatInShelterById(Mockito.anyLong())).thenReturn(cats);
        Mockito.when(catsInShelterService.findCatInShelterByName(Mockito.anyString())).thenReturn(cats);
        Mockito.when(catsInShelterService.getAllCatsInShelter()).thenReturn(animals);

        mockMvc.perform(
                        get("/catsInShelter?id={id}",1)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cats)));

        mockMvc.perform(
                        get("/catsInShelter?name=Бобик")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cats)));

        mockMvc.perform(
                        get("/catsInShelter"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(animals)));
    }
}