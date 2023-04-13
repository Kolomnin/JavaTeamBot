package com.example.javateambot.controllers;

import com.example.javateambot.entity.AnimalsInShelter;
import com.example.javateambot.service.AnimalsInShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("animalsInShelter")
public class AnimalsInShelterControllers {

    @GetMapping("greeting")
    public String greeting() {
        return "hello";
    }


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

    @PutMapping
    public ResponseEntity<AnimalsInShelter> editAnimalInShelter(@RequestBody AnimalsInShelter animal) {
        AnimalsInShelter foundAnimal = animalsInShelterService.editAnimalInShelter(animal);
        if (foundAnimal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundAnimal);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAnimalInShelter(@PathVariable Long id) {
        animalsInShelterService.deleteAnimalInShelter(id);
        return ResponseEntity.ok().build();
    }

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
