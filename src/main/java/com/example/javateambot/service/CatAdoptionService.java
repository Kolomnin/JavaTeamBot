package com.example.javateambot.service;

import com.example.javateambot.entity.AdoptedCats;
import com.example.javateambot.entity.Users;
import com.example.javateambot.repository.CatAdoptionRepository;
import com.example.javateambot.repository.CatsInShelterRepository;
import com.example.javateambot.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class CatAdoptionService {

    private CatsInShelterRepository catsInShelterRepository;


    private UsersRepository usersRepository;


    private CatAdoptionRepository catAdoptionRepository;


    @Autowired
    public CatAdoptionService( CatsInShelterRepository catsInShelterRepository, UsersRepository usersRepository, CatAdoptionRepository catAdoptionRepository) {
        this.catsInShelterRepository = catsInShelterRepository;
        this.usersRepository = usersRepository;
        this.catAdoptionRepository = catAdoptionRepository;
    }


// работает с этим кодом, но в нем не красиво добавляется в свагере

    public AdoptedCats adoptionCat2(Long userID, Long catId, AdoptedCats adoptedCats) {

        if (catsInShelterRepository.findById(catId).isPresent()||usersRepository.findById(userID).isPresent()){

            adoptedCats.setUsers(usersRepository.findById(userID).orElseThrow());
            adoptedCats.setCats(catsInShelterRepository.findById(catId).orElseThrow());
            adoptedCats.setLastDateProbationPeriod(LocalDate.now().plusDays(30));
            adoptedCats.setAdoptionDate(LocalDate.now());

        }

        return catAdoptionRepository.save(adoptedCats);
    }

    public Boolean checkChatId(Long chatId) {
        if (Objects.equals(usersRepository.findByChatId(chatId).getChatId(), chatId)) {
            return true ;
        } else return false;
    }

    public Users findUserByChatId(long chatId) {
        return usersRepository.findByChatId(chatId);
    }


}
