package com.example.javateambot.repository;

import com.example.javateambot.entity.Users;
import com.example.javateambot.service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersRepositoryTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersService usersService;

    private final UsersRepository repository = mock(UsersRepository.class);

    @Test
    public void testAddUser() {

        Users user = new Users();
        when(usersRepository.save(user)).thenReturn(user);
        Users result = usersService.addUser(user);
        assertNotNull(result);
        verify(usersRepository, times(1)).save(user);
    }

    @Test
    public void testEditUser() {
        Users user = new Users();
        when(usersRepository.save(user)).thenReturn(user);
        Users result = usersService.editUser(user);
        assertNotNull(result);
        verify(usersRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        long id = 1L;
        doNothing().when(usersRepository).deleteById(id);
        usersService.deleteUser(id);
        verify(usersRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetAllUsers() {
        List<Users> users = new ArrayList<>();
        when(usersRepository.findAll()).thenReturn(users);
        Collection<Users> result = usersService.getAllUsers();
        assertNotNull(result);
        assertEquals(result.size(), users.size());
        verify(usersRepository, times(1)).findAll();
    }

    @Test
    public void testFindUsersById() {
        long id = 1L;
        Users user = new Users();
        when(usersRepository.findById(id)).thenReturn(user);
        Users result = usersService.findUsersById(id);
        assertNotNull(result);
        assertEquals(result, user);
        verify(usersRepository, times(1)).findById(id);
    }
}
