package com.example.javateambot.service;

import com.example.javateambot.entity.AdoptedDogs;
import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DogAdoptionServiceTest {

    private final UsersRepository repository = mock(UsersRepository.class);

    @MockBean
    AdoptedDogs adoptedDogs;


    @Test
    void adoptionDog2() {
    }

//    @Test
//    void checkChatId() {
//
//        Users user = new Users();
//        user.setUsers(1);
//        user.setChatId(123L);
//        when(repository.save(user)).thenReturn(user);
//        repository.save(user);
//        assertEquals(repository.getReferenceById(1L).getChatId());
//        assertEquals(repository.findByChatId(12L));
//    }
//    @Test
//    public void testAdoptionDog2WithValidInput() {
//        // Arrange
//        Long userID = 1L;
//        Long animalId = 2L;
//        AdoptedDogs adoptedDogs = new AdoptedDogs();
//        adoptedDogs.setUsers(1L);
//        adoptedDogs.setIdAnimal(2L);
//
//        adoptedDogs.setLastDateProbationPeriod(LocalDate.now());
//
//        // Act
//        AdoptedDogs savedAdoptedDogs = adoptionDog2(userID, animalId, adoptedDogs);
//
//        // Assert
//        assertNotNull(savedAdoptedDogs);
//        assertEquals(userID, savedAdoptedDogs.getUsers());
//        assertEquals(animalId, savedAdoptedDogs.getIdAnimal());
//        assertNotNull(savedAdoptedDogs.getLastDateProbationPeriod());
//    }
//
//    @Test
//    private AdoptedDogs adoptionDog2(Long userID, Long animalId, AdoptedDogs adoptedDogs) {
//
//        adoptedDogs.setLastDateProbationPeriod(LocalDate.now());
//        adoptedDogs.setUsers(userID);
//        adoptedDogs.setIdAnimal(animalId);
//    return adoptedDogs; }
//
//    @Test
//    public void testAdoptionDog2WithNonexistentAnimal() {
//        // Arrange
//        Long userID = 1L;
//        Long animalId = 1L; // non-existent animal ID
//        AdoptedDogs adoptedDogs = new AdoptedDogs();
//        adoptedDogs.setUsers(userID);
//        adoptedDogs.setIdAnimal(1);
//
//        // Act
//        AdoptedDogs savedAdoptedDogs = adoptionDog2(userID, animalId, adoptedDogs);
//
//        // Assert
//        assertNotNull(savedAdoptedDogs);
//        assertEquals(adoptedDogs, savedAdoptedDogs);
//    }
//
//    @Test
//    public void testAdoptionDog2WithNonexistentUser() {
//        // Arrange
//        Long userID = 1L; // non-existent user ID
//        Long animalId = 2L;
//        AdoptedDogs adoptedDogs = new AdoptedDogs();
//        adoptedDogs.setIdAnimal(animalId);
//        adoptedDogs.setUsers(userID);
//
//        // Act
//        AdoptedDogs savedAdoptedDogs = adoptionDog2(userID, animalId, adoptedDogs);
//
//        // Assert
//        assertNull(savedAdoptedDogs);
//    }
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
//        assertEquals(userID, updatedAnimalsInHouse.getUsers());
//        assertEquals(animalId, updatedAnimalsInHouse.getIdAnimal());
//        assertNotEquals(originalDate, updatedAnimalsInHouse.getLastDateProbationPeriod());
//    }
}

//        public Boolean checkChatId(Long chatId) {
//            if (Objects.equals(usersRepository.findByChatId(chatId).getChatId(), chatId)) {
//                return true ;
//            } else return false;
//        }
