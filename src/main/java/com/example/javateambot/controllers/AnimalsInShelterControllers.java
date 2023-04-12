package com.example.javateambot.controllers;

import com.example.javateambot.entity.AnimalsInHouse;
import com.example.javateambot.entity.AnimalsInShelter;
import com.example.javateambot.service.AnimalsInShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;


@RestController
@RequestMapping("/animalsInShelter")
public class AnimalsInShelterControllers {

    private final AnimalsInShelterService animalsInShelterService;

    public AnimalsInShelterControllers(AnimalsInShelterService animalsInShelterService) {
        this.animalsInShelterService = animalsInShelterService;
    }

    @Operation(summary = "Добавление нового животного в приют",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Животное, которое добавляем в приют",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
//                    schema = @Schema (implementation = AnimalsInShelter.class)
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Животное успешно добавлено")
            }

    )
    @PostMapping
    public AnimalsInShelter addAnimalsInShelter(@RequestBody AnimalsInShelter animal) {
        return animalsInShelterService.addAnimalInShelter(animal);
    }

    @Operation(summary = "Изменение параметров животного",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Животное которое добавляем в приют",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
//                    schema = @Schema (implementation = AnimalsInShelter.class)
            )
    )
    @PutMapping
    public ResponseEntity<AnimalsInShelter> editAnimalInShelter(@RequestBody AnimalsInShelter animal) {
        AnimalsInShelter foundAnimal = animalsInShelterService.editAnimalInShelter(animal);
        if (foundAnimal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundAnimal);
    }

    @Operation(summary = "Удаление животного по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Животное успаешно удалено",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если животное не найдено"

                    )
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity deleteAnimalInShelter(@Parameter(description = "id животного", example = "1")
                                                @PathVariable Long id) {
        animalsInShelterService.deleteAnimalInShelter(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Поиск животного по имени или id в приюте",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденное животное",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE)

                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Если животное не найдено"

                    )
            }
    )
    @GetMapping
    public ResponseEntity findAnimalsInShelter(@Parameter(description = "id животного", example = "1") @RequestParam(required = false) Long id,
                                               @Parameter(description = "Имя животного", example = "Шарик") @RequestParam(required = false) String name) {
        if (id != null) {
            return ResponseEntity.ok(animalsInShelterService.findAnimalInShelterById(id));
        }
        if (name != null) {
            return ResponseEntity.ok(animalsInShelterService.findAnimalInShelterByName(name));
        }
        return ResponseEntity.ok(animalsInShelterService.getAllAnimalsInShelter());
    }
}
