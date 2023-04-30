package com.example.javateambot.service;

import com.example.javateambot.entity.CatsInShelter;
import com.example.javateambot.repository.CatsInShelterRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CatsInShelterService {
    private final CatsInShelterRepository catsInShelterRepository;


    public CatsInShelterService(CatsInShelterRepository catsInShelterRepository) {
        this.catsInShelterRepository = catsInShelterRepository;
    }

    /**
     * Метод, который добавляет кошку в базу приюта
     * @param cat кошка, которую необходимо добавить в базу
     * @return добавленная кошка
     */
    public CatsInShelter addCatInShelter(CatsInShelter cat) {
        return catsInShelterRepository.save(cat);
    }

    /**
     * Метод, который вносит изменения в карточку кошки в базе приюта
     * @param cat кошка, в карточку которой вносятся изменения
     * @return кошка, в карточку которой были внесены изменения
     */
    public CatsInShelter editCatInShelter(CatsInShelter cat) {
        return catsInShelterRepository.save(cat);
    }

    /**
     * Метод, который удаляет кошку из базы приюта
     * @param id идентификатор кошки
     */
    public void deleteCatInShelter(long id) {
        catsInShelterRepository.deleteById(id);
    }

    /**
     * Метод, который выводит всех кошек из базы приюта
     * @return всех кошек из базы приюта
     */
    public Collection<CatsInShelter> getAllCatsInShelter() {
        return catsInShelterRepository.findAll();
    }

    /**
     * Метод, который ищет кошку в базе приюта по ID
     * @return найденная кошка с искомым id
     */
    public CatsInShelter findCatInShelterById(long id) {
        return catsInShelterRepository.findById(id).orElseThrow();
    }

    /**
     * Метод, который ищет кошаку в базе приюта по имени
     * @return найденная кошка с искомым именем
     */
    public CatsInShelter findCatInShelterByName(String name) {
        return catsInShelterRepository.findByNameCat(name);
    }


}
