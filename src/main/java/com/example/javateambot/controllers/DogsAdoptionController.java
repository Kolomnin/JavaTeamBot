package com.example.javateambot.controllers;


import com.example.javateambot.entity.AdoptedDogs;
import com.example.javateambot.entity.DogsInShelter;
import com.example.javateambot.service.DogAdoptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/DogAdoption")
public class DogsAdoptionController {

    private final Logger logger = LoggerFactory.getLogger(DogsAdoptionController.class);

    private DogAdoptionService dogAdoptionService;

    @Autowired
    public DogsAdoptionController(DogAdoptionService dogAdoptionService) {
        this.dogAdoptionService = dogAdoptionService;
    }
    @Operation(summary = "Присваивание владельцу собаки, в которое вносится id собаки (из бд) и владельца (из бд)",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Присваивание владельцу собаки",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Собака успешно добавлено владельцу"),
                    @ApiResponse(responseCode = "400",
                            description = "Собака не присвоена владельцу")
            }
    )
    @PostMapping
    public AdoptedDogs addDogsInShelter2(@Parameter(description = "id владельца, которое присваиваем владельцу", example = "1")
                                               @RequestParam(required = false) Long userId,
                                            @Parameter(description = "id собаки", example = "1")
                                               @RequestParam(required = false) Long dogId
            , @RequestBody AdoptedDogs adoptedDogs) {
        return dogAdoptionService.adoptionDog2(userId, dogId, adoptedDogs);
    }

    @PutMapping
    public void increaseProbationPeriod(@RequestParam(required = false) Long idDog,
                                        @RequestParam(required = false) int daysToIncrease) {
        dogAdoptionService.increaseProbationPeriodDog(idDog, daysToIncrease);
    }


}
