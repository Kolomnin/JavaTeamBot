package com.example.javateambot.controllers;

import com.example.javateambot.entity.AdoptedCats;
import com.example.javateambot.entity.CatsInShelter;
import com.example.javateambot.entity.Users;
import com.example.javateambot.service.CatAdoptionService;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class CatsAdoptionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @MockBean
    CatAdoptionService catAdoptionService;

    @Test
    void addCatsInShelter2() {
    }

    @Test
    void addAnimalInShelter() throws Exception{
        AdoptedCats cats = new AdoptedCats();
        Users user = new Users();
        user.setIdUser(1L);
        cats.setUsers(user);
        CatsInShelter cat = new CatsInShelter();
        cat.setIdCat(1L);
        cats.setCats(cat);


        Mockito.when(catAdoptionService.adoptionCat2(user.getIdUser(),cat.getIdCat(),cats)).thenReturn(cats);

        mockMvc.perform(
                        post("/CatAdoption")
                                .content(objectMapper.writeValueAsString(cats))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(cats)));

    }
    @Test
    public void testAddCatsInShelter2() {
        AdoptedCats adoptedCats = new AdoptedCats();
        Users user = new Users();
        user.setIdUser(1L);
        adoptedCats.setUsers(user);
        CatsInShelter cat = new CatsInShelter();
        cat.setIdCat(1L);
        adoptedCats.setCats(cat);


        AdoptedCats result = catAdoptionService.adoptionCat2(user.getIdUser(),cat.getIdCat(), adoptedCats);

        assertNotNull(result);
        assertEquals(1L, result.getUsers().getIdUser());
        assertEquals(1L, result.getCats().getIdCat());
    }
}