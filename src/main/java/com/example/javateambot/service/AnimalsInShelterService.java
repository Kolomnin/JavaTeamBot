package com.example.javateambot.service;

import com.example.javateambot.entity.AnimalsInShelter;
import com.example.javateambot.repository.AnimalsInShelterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AnimalsInShelterService {
    private final AnimalsInShelterRepository animalsInShelterRepository;

    public AnimalsInShelterService(AnimalsInShelterRepository animalsInShelterRepository) {
        this.animalsInShelterRepository = animalsInShelterRepository;
    }

   /**
     * Метод, который добавляет животное в базу приюта
     * @param animal животное, которое необходимо добавить в базу
     * @return добавленное животное
     */
    public AnimalsInShelter addAnimalInShelter(AnimalsInShelter animal) {
        return animalsInShelterRepository.save(animal);
    }

    /**
     * Метод, который вносит изменения в карточку животного в базе приюта
     * @param animal животное, в карточку которого вносятся изменения
     * @return животное, в карточку которого были внесены изменения
     */
    public AnimalsInShelter editAnimalInShelter(AnimalsInShelter animal) {
        return animalsInShelterRepository.save(animal);
    }

    /**
     * Метод, который удаляет животное из базы приюта
     * @param id идентификатор животного
     */
    public void deleteAnimalInShelter(long id) {
        animalsInShelterRepository.deleteById(id);
    }

    /**
     * Метод, который выводит всех животных из базы приюта
     * @return всех животных из базы приюта
     */
    public Collection<AnimalsInShelter> getAllAnimalsInShelter() {
        return animalsInShelterRepository.findAll();
    }

    /**
     * Метод, который ищет животное в базе приюта по ID
     * @return найденное животное с искомым id
     */
    public AnimalsInShelter findAnimalInShelterById(long id) {
        return animalsInShelterRepository.findById(id);
    }

    /**
     * Метод, который ищет животное в базе приюта по имени
     * @return найденное животное с искомым именем
     */
    public AnimalsInShelter findAnimalInShelterByName(String name) {
        return animalsInShelterRepository.findByNameAnimal(name);
    }


}
