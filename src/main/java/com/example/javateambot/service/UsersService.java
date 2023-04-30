package com.example.javateambot.service;

import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UsersService {
    private final UsersRepository usersRepository;


    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Метод, который добавляет владельца в базу
     * @param user владелец, которого необходимо добавить в базу
     * @return добавленный владелец
     */
    public Users addUser(Users user) {
        return usersRepository.save(user);
    }

    /**
     * Метод, который вносит изменения в карточку владельца в базе
     * @param user владелец, в карточку которого вносятся изменения
     * @return владелец, в карточку которого были внесены изменения
     */
    public Users editUser(Users user) {
        return usersRepository.save(user);
    }

    /**
     * Метод, который удаляет владельца из базы
     * @param id идентификатор живвладельца
     */
    public void deleteUser(long id) {
        usersRepository.deleteById(id);
    }

    /**
     * Метод, который выводит всех владельцев из базы
     * @return все владельцы из базы
     */
    public Collection<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    /**
     * Метод, который ищет владельца в базе приюта по ID
     * @return владельца с искомым id
     */
    public Users findUsersById(long id) {
        return usersRepository.findById(id);
    }
}
