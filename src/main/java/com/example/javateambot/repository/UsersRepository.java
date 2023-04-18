package com.example.javateambot.repository;


import com.example.javateambot.entity.AnimalsInShelter;
import com.example.javateambot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findById(long id);

    Users findByNumberUser(String numberUser);

    Users findByChatId (Long chatId);

//    Boolean findByNumberUseraOrderBy (String number);
}

