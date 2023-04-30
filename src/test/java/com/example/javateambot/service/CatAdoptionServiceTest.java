package com.example.javateambot.service;

import com.example.javateambot.entity.AdoptedCats;
import com.example.javateambot.entity.CatsInShelter;
import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.CatAdoptionRepository;
import com.example.javateambot.repository.CatsInShelterRepository;
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
class CatAdoptionServiceTest {

    @Mock
    private CatsInShelterRepository catsInShelterRepository;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private CatAdoptionRepository catAdoptionRepository;

    @InjectMocks
    private CatAdoptionService catAdoptionService;

    private AdoptedCats adoptedCats;

    @BeforeEach
    void setUp() {
        adoptedCats = new AdoptedCats();
    }

    @Test
    void testAdoptionCat2() {
        Long userId = 1L;
        Long catId = 1L;
        CatsInShelter cat = new CatsInShelter();
        cat.setNameCat("murzik");
        cat.setAge(2);
        cat.setIdCat(2);
        Users user = new Users();
        user.setIdUser(1);
        user.setNumberUser("222");
        user.setFirstName("oleg");
        when(catsInShelterRepository.findById(catId)).thenReturn(Optional.of(cat));
        when(usersRepository.findById(userId)).thenReturn(Optional.of(user));

        AdoptedCats result = catAdoptionService.adoptionCat2(userId, catId, adoptedCats);

//        verify(catsInShelterRepository, times(1)).findById(catId);
//        verify(usersRepository, times(1)).findById(userId);
//        verify(catAdoptionRepository, times(1)).save(adoptedCats);

        assertNotNull(result);
        assertEquals(LocalDate.now().plusDays(30), result.getLastDateProbationPeriod());
    }

    @Test
    public void testAdoptionCat2_ValidUserAndCatIDs_ShouldReturnAdoptedCat() {
        // Arrange
        Long userID = 1L;
        Long catId = 1L;
        CatsInShelter cat = new CatsInShelter();
        cat.setNameCat("murzik");
        cat.setAge(2);
        cat.setIdCat(1);
        Users user = new Users();
        user.setIdUser(1);
        user.setNumberUser("222");
        user.setFirstName("oleg");
        AdoptedCats adoptedCats = new AdoptedCats();
        when(catsInShelterRepository.findById(catId)).thenReturn(Optional.of(cat));
        when(usersRepository.findById(userID)).thenReturn(Optional.of(user));
        when(catAdoptionRepository.save(Mockito.any())).thenReturn(adoptedCats);
        // Act
        AdoptedCats result = catAdoptionService.adoptionCat2(userID, catId, adoptedCats);
        // Assert
        assertNotNull(result);
        assertEquals(userID, result.getUsers().getIdUser());
        assertEquals(catId, result.getCats().getIdCat());
        assertNotNull(result.getLastDateProbationPeriod());
    }

    @Test
    public void testAdoptionCat2_InvalidUserID_ShouldThrowException() {
        // Arrange
        Long userID = 1L;
        Long catId = 1L;

        CatsInShelter cat = new CatsInShelter();
        cat.setNameCat("murzik");
        cat.setAge(2);
        cat.setIdCat(1);
        AdoptedCats adoptedCats = new AdoptedCats();
        Mockito.when(catsInShelterRepository.findById(catId)).thenReturn(Optional.of(cat));
        Mockito.when(usersRepository.findById(userID)).thenReturn(Optional.empty());
        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> catAdoptionService.adoptionCat2(userID, catId, adoptedCats));

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

//        when(usersRepository.findByChatId(chatId)).thenReturn(user);

        usersRepository.save(user);


        boolean result = catAdoptionService.checkChatId(chatId);

        verify(usersRepository, times(1)).findByChatId(chatId);

        assertTrue(result);
    }

    @Test
    void testCheckChatIdWhenChatIdIsNotFound() {
        Long chatId = 1L;

        when(usersRepository.findByChatId(chatId)).thenReturn(null);

        boolean result = catAdoptionService.checkChatId(chatId);

        verify(usersRepository, times(1)).findByChatId(chatId);

        assertFalse(result);
    }
}


