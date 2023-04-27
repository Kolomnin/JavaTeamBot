package com.example.javateambot.controllers;

import com.example.javateambot.entity.AnimalsInShelter;
import com.example.javateambot.service.AnimalsInShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("animalsInShelter")
public class AnimalsInShelterControllers {

    private final AnimalsInShelterService animalsInShelterService;

    public AnimalsInShelterControllers(AnimalsInShelterService animalsInShelterService) {
        this.animalsInShelterService = animalsInShelterService;
    }

    @Operation(summary = "Добавление нового животного в приют",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Животное, которое добавляем в приют",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ), responses = {
            @ApiResponse(responseCode = "200", description = "Животное успешно добавлено")
    })
    @PostMapping
    public AnimalsInShelter addAnimalsInShelter(@RequestBody AnimalsInShelter animal) {
        return animalsInShelterService.addAnimalInShelter(animal);
    }

    @Operation(summary = "Изменение параметров животного",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Новое животное",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ))
    @PutMapping
    public ResponseEntity<AnimalsInShelter> editAnimalInShelter(@RequestBody AnimalsInShelter animal) {
        AnimalsInShelter foundAnimal = animalsInShelterService.editAnimalInShelter(animal);
        if (foundAnimal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundAnimal);
    }

    @Operation(summary = "Удаление животного по id",
            responses = {@ApiResponse(
                    responseCode = "200", description = "Животное успешно удалено",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)

            ), @ApiResponse(
                    responseCode = "404", description = "Если животное не найдено"
            )
            })
    @DeleteMapping("{id}")
    public ResponseEntity<AnimalsInShelter> deleteAnimalInShelter(@PathVariable Long id) {
        animalsInShelterService.deleteAnimalInShelter(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Поиск животного по id или кличке", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Найденное животное", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
            ), @ApiResponse(
            responseCode = "404", description = "Если животное не найдено"
    )
    })
    @GetMapping
    public ResponseEntity findAnimalsInShelter(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) String name) {
        if (id != null) {
            return ResponseEntity.ok(animalsInShelterService.findAnimalInShelterById(id));
        }
        if (name != null) {
            return ResponseEntity.ok(animalsInShelterService.findAnimalInShelterByName(name));
        }
        return ResponseEntity.ok(animalsInShelterService.getAllAnimalsInShelter());
    }
}
