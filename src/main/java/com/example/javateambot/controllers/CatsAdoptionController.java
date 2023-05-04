package com.example.javateambot.controllers;


import com.example.javateambot.entity.AdoptedCats;
import com.example.javateambot.service.CatAdoptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CatAdoption")
public class CatsAdoptionController {

    private CatAdoptionService catAdoptionService;

    @Autowired
    public CatsAdoptionController(CatAdoptionService catAdoptionService) {
        this.catAdoptionService = catAdoptionService;
    }


    @Operation(summary = "Присваивание владельцу кошки, в которое вносится id кошки (из бд) и владельца (из бд)",

            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Присваивание владельцу кошки",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Кошка успешно добавлена владельцу"),
                    @ApiResponse(responseCode = "400",
                            description = "Кошка не присвоена владельцу")
            }
    )
    @PostMapping
    public AdoptedCats addCatsInShelter2(@Parameter(description = "id владельца, которое присваиваем владельцу", example = "1")
                                        @RequestParam(required = false) Long userId,
                                        @Parameter(description = "id кота", example = "1")
                                        @RequestParam(required = false) Long catId
            , @RequestBody AdoptedCats adoptedCats) {
        return catAdoptionService.adoptionCat2(userId, catId, adoptedCats);
    }

    @PutMapping
    public void increaseProbationPeriod(@RequestParam(required = false) Long idCat,
                                        @RequestParam(required = false) int daysToIncrease) {
        catAdoptionService.increaseProbationPeriodCat(idCat, daysToIncrease);
    }


}
