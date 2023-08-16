package com.example.javateambot.controllers;

import com.example.javateambot.entity.DogsInShelter;
import com.example.javateambot.service.DogsInShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("dogsInShelter")
public class DogsInShelterController {
    private final Logger logger = LoggerFactory.getLogger(DogsInShelterController.class);

    private final DogsInShelterService dogsInShelterService;

    public DogsInShelterController(DogsInShelterService dogsInShelterService) {
        this.dogsInShelterService = dogsInShelterService;
    }
    @Operation(summary = "Добавление новой собаки в приют",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Собака, которую добавляем в приют",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ), responses = {
            @ApiResponse(responseCode = "200", description = "Собака успешно добавлено")
    })
    @PostMapping
    public DogsInShelter addDogsInShelter(@RequestBody DogsInShelter dog) {
        return dogsInShelterService.addDogInShelter(dog);
    }

    @Operation(summary = "Изменение параметров собаки",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Новая собака",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ))
    @PutMapping
    public ResponseEntity<DogsInShelter> editDogInShelter(@RequestBody DogsInShelter dog) {
        DogsInShelter foundDog = dogsInShelterService.editDogInShelter(dog);
        if (foundDog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundDog);
    }

    @Operation(summary = "Удаление собаки по id",
            responses = {@ApiResponse(
                    responseCode = "200", description = "Собака успешно удалена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)

            ), @ApiResponse(
                    responseCode = "404", description = "Если собака не найдена"
            )
            })
    @DeleteMapping("{id}")
    public ResponseEntity<DogsInShelter> deleteDogInShelter(@PathVariable Long id) {
        dogsInShelterService.deleteDogInShelter(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Поиск собаки по id или кличке", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Найденная собака", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
            ), @ApiResponse(
            responseCode = "404", description = "Если животное не найдено"
    )
    })
    @GetMapping
    public ResponseEntity findDogsInShelter(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) String name) {
        if (id != null) {
            return ResponseEntity.ok(dogsInShelterService.findDogInShelterById(id));
        }
        if (name != null) {
            return ResponseEntity.ok(dogsInShelterService.findDogInShelterByName(name));
        }
        return ResponseEntity.ok(dogsInShelterService.getAllDogsInShelter());
    }
}
