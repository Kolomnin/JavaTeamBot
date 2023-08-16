package com.example.javateambot.service;

import com.example.javateambot.entity.*;
import com.example.javateambot.repository.DogAdoptionRepository;
import com.example.javateambot.repository.DogsInShelterRepository;
import com.example.javateambot.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogAdoptionServiceTest {

    @Mock
    private DogsInShelterRepository dogsInShelterRepository;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private DogAdoptionRepository dogAdoptionRepository;

    @InjectMocks
    private DogAdoptionService dogAdoptionService;

    private AdoptedDogs adoptedDogs;

    @BeforeEach
    void setUp() {
        adoptedDogs = new AdoptedDogs();
    }

    @Test
    void testAdoptionCat2() {
        Long userId = 1L;
        Long catId = 1L;
        DogsInShelter dogs = new DogsInShelter();
        dogs.setNameDog("biba");
        dogs.setAge(2);
        dogs.setIdDog(2);
        Users user = new Users();
        user.setIdUser(1);
        user.setNumberUser("222");
        user.setFirstName("oleg");
        when(dogsInShelterRepository.findById(catId)).thenReturn(Optional.of(dogs));
        when(usersRepository.findById(userId)).thenReturn(Optional.of(user));

        AdoptedDogs result = dogAdoptionService.adoptionDog2(userId, catId, adoptedDogs);

//        verify(dogsInShelterRepository, times(1)).findById(catId);
//        verify(usersRepository, times(1)).findById(userId);
//        verify(dogAdoptionRepository, times(1)).save(adoptedDogs);

        assertNotNull(result);
        assertEquals(LocalDate.now().plusDays(30), result.getLastDateProbationPeriod());
    }

    @Test
    public void testAdoptionCat2_ValidUserAndCatIDs_ShouldReturnAdoptedCat() {
        // Arrange
        Long userID = 1L;
        Long dogId = 1L;
        DogsInShelter dogs = new DogsInShelter();
        dogs.setNameDog("biba");
        dogs.setAge(2);
        dogs.setIdDog(1);
        Users user = new Users();
        user.setIdUser(1);
        user.setNumberUser("222");
        user.setFirstName("oleg");
        AdoptedDogs adoptedDogs2 = new AdoptedDogs();
        when(dogsInShelterRepository.findById(dogId)).thenReturn(Optional.of(dogs));
        when(usersRepository.findById(userID)).thenReturn(Optional.of(user));
        when(dogAdoptionRepository.save(Mockito.any())).thenReturn(adoptedDogs2);
        // Act
        AdoptedDogs result = dogAdoptionService.adoptionDog2(userID, dogId, adoptedDogs2);
        // Assert
        assertNotNull(result);
        assertEquals(userID, result.getUsers().getIdUser());
        assertEquals(dogId, result.getDogs().getIdDog());
        assertNotNull(result.getLastDateProbationPeriod());
    }

    @Test
    public void testAdoptionCat2_InvalidUserID_ShouldThrowException() {
        // Arrange
        Long userID = 1L;
        Long catId = 1L;

        DogsInShelter dogs = new DogsInShelter();
        dogs.setNameDog("biba");
        dogs.setAge(2);
        dogs.setIdDog(1);
        AdoptedCats adoptedCats = new AdoptedCats();
        Mockito.when(dogsInShelterRepository.findById(catId)).thenReturn(Optional.of(dogs));
        Mockito.when(usersRepository.findById(userID)).thenReturn(Optional.empty());
        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> dogAdoptionService.adoptionDog2(userID, catId, adoptedDogs));

    }

    @Test
    void testCheckChatIdWhenChatIdIsFound() {

        CatsInShelter catsInShelter = mock(CatsInShelter.class);

        Long chatId = 23123421L;
        Users user = new Users();
        user.setIdUser(1);
        user.setNumberUser("222");
        user.setFirstName("oleg");
        user.setChatId(23123421L);

        when(usersRepository.findByChatId(chatId)).thenReturn(user);

        usersRepository.save(user);


        boolean result = dogAdoptionService.checkChatId(chatId);

        verify(usersRepository, times(1)).findByChatId(chatId);

        assertTrue(result);
    }

    @Test
    void testCheckChatIdWhenChatIdIsNotFound() {
        Long chatId = 1L;

        when(usersRepository.findByChatId(chatId)).thenReturn(null);

        boolean result = dogAdoptionService.checkChatId(chatId);

        verify(usersRepository, times(1)).findByChatId(chatId);

        assertFalse(result);
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
//        AdoptedCats animalsInHouse = new AdoptedCats();
//        animalsInHouse.se("Buddy");
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
