package com.example.javateambot.service;

import com.example.javateambot.entity.AnimalsInHouse;
import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.UsersRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DogAdoptionServiceTest {

    private final UsersRepository repository = mock(UsersRepository.class);

    @MockBean
    AnimalsInHouse animalsInHouse;


    @Test
    void adoptionDog2() {
    }

    @Test
    void checkChatId() {

        Users user = new Users();
        user.setIdUser(1);
        user.setChatId(12L);
//        when(repository.save(user)).thenReturn(user);
        repository.save(user);
        assertEquals(repository.getReferenceById(1L).getChatId(),user.getChatId());
//        assertEquals(repository.findByChatId(12L));
    }
    @Test
    public void testAdoptionDog2WithValidInput() {
        // Arrange
        Long userID = 1L;
        Long animalId = 2L;
        AnimalsInHouse animalsInHouse = new AnimalsInHouse();
        animalsInHouse.setIdUser(1L);
        animalsInHouse.setIdAnimal(2L);
        animalsInHouse.setLastText("adoption");
        animalsInHouse.setLastDateProbationPeriod(LocalDate.now());

        // Act
        AnimalsInHouse savedAnimalsInHouse = adoptionDog2(userID, animalId, animalsInHouse);

        // Assert
        assertNotNull(savedAnimalsInHouse);
        assertEquals(userID, savedAnimalsInHouse.getIdUser());
        assertEquals(animalId, savedAnimalsInHouse.getIdAnimal());
        assertNotNull(savedAnimalsInHouse.getLastDateProbationPeriod());
    }

    private AnimalsInHouse adoptionDog2(Long userID, Long animalId, AnimalsInHouse animalsInHouse) {

        animalsInHouse.setLastDateProbationPeriod(LocalDate.now());
        animalsInHouse.setIdUser(userID);
        animalsInHouse.setIdAnimal(animalId);
    return animalsInHouse; }

    @Test
    public void testAdoptionDog2WithNonexistentAnimal() {
        // Arrange
        Long userID = 1L;
        Long animalId = 1L; // non-existent animal ID
        AnimalsInHouse animalsInHouse = new AnimalsInHouse();
        animalsInHouse.setIdUser(userID);
        animalsInHouse.setIdAnimal(1);

        // Act
        AnimalsInHouse savedAnimalsInHouse = adoptionDog2(userID, animalId, animalsInHouse);

        // Assert
        assertNotNull(savedAnimalsInHouse);
        assertEquals(animalsInHouse,savedAnimalsInHouse);
    }

    @Test
    public void testAdoptionDog2WithNonexistentUser() {
        // Arrange
        Long userID = 1L; // non-existent user ID
        Long animalId = 2L;
        AnimalsInHouse animalsInHouse = new AnimalsInHouse();
        animalsInHouse.setIdAnimal(animalId);
        animalsInHouse.setIdUser(userID);

        // Act
        AnimalsInHouse savedAnimalsInHouse = adoptionDog2(userID, animalId, animalsInHouse);

        // Assert
        assertNull(savedAnimalsInHouse);
    }
//
//    @Test
//    public void testAdoptionDog2WithExistingData() {
//        // Arrange
//        Long userID = 1L;
//        Long animalId = 2L;
//        AnimalsInHouse animalsInHouse = new AnimalsInHouse();
//        animalsInHouse.setName("Buddy");
//        animalsInHouse.setAge(2);
//        animalsInHouse.setSpecies("Dog");
//
//        // Save the AnimalsInHouse object
//        AnimalsInHouse savedAnimalsInHouse = adoptionDog2(userID, animalId, animalsInHouse);
//
//        // Update the last probation period date
//        LocalDate originalDate = savedAnimalsInHouse.getLastDateProbationPeriod();
//        savedAnimalsInHouse.setLastDateProbationPeriod(originalDate.minusDays(15));
//
//        // Act
//        AnimalsInHouse updatedAnimalsInHouse = adoptionDog2(userID, animalId, savedAnimalsInHouse);
//
//        // Assert
//        assertNotNull(updatedAnimalsInHouse);
//        assertEquals(userID, updatedAnimalsInHouse.getIdUser());
//        assertEquals(animalId, updatedAnimalsInHouse.getIdAnimal());
//        assertNotEquals(originalDate, updatedAnimalsInHouse.getLastDateProbationPeriod());
//    }
}

//        public Boolean checkChatId(Long chatId) {
//            if (Objects.equals(usersRepository.findByChatId(chatId).getChatId(), chatId)) {
//                return true ;
//            } else return false;
//        }
