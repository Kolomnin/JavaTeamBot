package com.example.javateambot.service;

import com.example.javateambot.entity.DogsInShelter;
import com.example.javateambot.repository.DogsInShelterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DogsInShelterService {
    private final DogsInShelterRepository dogsInShelterRepository;

    public DogsInShelterService(DogsInShelterRepository dogsInShelterRepository) {
        this.dogsInShelterRepository = dogsInShelterRepository;
    }

   /**
     * Метод, который добавляет собаку в базу приюта
     * @param dog собака, которую необходимо добавить в базу
     * @return добавленная собака
     */
    public DogsInShelter addDogInShelter(DogsInShelter dog) {
        return dogsInShelterRepository.save(dog);
    }

    /**
     * Метод, который вносит изменения в карточку собаки в базе приюта
     * @param dog собака, в карточку которой вносятся изменения
     * @return собакае, в карточку которой были внесены изменения
     */
    public DogsInShelter editDogInShelter(DogsInShelter dog) {
        return dogsInShelterRepository.save(dog);
    }

    /**
     * Метод, который удаляет собаку из базы приюта
     * @param id идентификатор собаки
     */
    public void deleteDogInShelter(long id) {
        dogsInShelterRepository.deleteById(id);
    }

    /**
     * Метод, который выводит всех собак из базы приюта
     * @return всех собак из базы приюта
     */
    public Collection<DogsInShelter> getAllDogsInShelter() {
        return dogsInShelterRepository.findAll();
    }

    /**
     * Метод, который ищет собаку в базе приюта по ID
     * @return найденная собака с искомым id
     */
    public DogsInShelter findDogInShelterById(long id) {
        return dogsInShelterRepository.findById(id).orElseThrow();
    }

    /**
     * Метод, который ищет собаку в базе приюта по имени
     * @return найденная собака с искомым именем
     */
    public DogsInShelter findDogInShelterByName(String name) {
        return dogsInShelterRepository.findByNameDog(name);
    }


}
