package com.example.javateambot.controllers;


import com.example.javateambot.entity.AnimalsInHouse;
import com.example.javateambot.repository.AnimalsInHouseRepository;
import com.example.javateambot.service.DogAdoptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/DogAdoption")
public class DogAdoptionController {


    private DogAdoptionService dogAdoptionService;
    @Autowired
    public DogAdoptionController(DogAdoptionService dogAdoptionService) {
        this.dogAdoptionService = dogAdoptionService;
    }

    @Operation(summary = "Присваивание владельцу животного, в которое вносится id животного (из бд) и владельца (из бд)",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Присваивание владельцу животного",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
//                    schema = @Schema (implementation = AnimalsInShelter.class)
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Животное успешно присвоено владельцу добавлено"),
                    @ApiResponse(responseCode = "400",
                            description = "Животное не присвоено добавлено")
            }




    )
    @PostMapping
    public AnimalsInHouse addAnimalsInShelter2(@Parameter(description = "id животного которое присваиваем владельцу", example = "1")
                                               @RequestParam(required = false) Long userID,
                                               @Parameter(description = "id владельца", example = "1")
                                               @RequestParam(required = false) Long animalID
            , @RequestBody AnimalsInHouse animalsInHouse) {
        return dogAdoptionService.adoptionDog2(userID, animalID, animalsInHouse);
    }


}
