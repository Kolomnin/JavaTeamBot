package com.example.javateambot.controllers;

import com.example.javateambot.entity.CatsInShelter;
import com.example.javateambot.service.CatsInShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("catsInShelter")
public class CatsInShelterController {

    private final CatsInShelterService catsInShelterService;

    public CatsInShelterController(CatsInShelterService catsInShelterService) {
        this.catsInShelterService = catsInShelterService;
    }

    @Operation(summary = "Добавление новой кошки в приют",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Кошка, которую добавляем в приют",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ), responses = {
            @ApiResponse(responseCode = "200", description = "Кошка успешно добавлено")
    })
    @PostMapping
    public CatsInShelter addCatsInShelter(@RequestBody CatsInShelter cat) {
        return catsInShelterService.addCatInShelter(cat);
    }

    @Operation(summary = "Изменение параметров кошки",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Новая кошка",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ))
    @PutMapping
    public ResponseEntity<CatsInShelter> editCatInShelter(@RequestBody CatsInShelter cat) {
        CatsInShelter foundCat = catsInShelterService.editCatInShelter(cat);
        if (foundCat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundCat);
    }

    @Operation(summary = "Удаление кошки по id",
            responses = {@ApiResponse(
                    responseCode = "200", description = "Кошка успешно удалена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)

            ), @ApiResponse(
                    responseCode = "404", description = "Если кошка не найдена"
            )
            })
    @DeleteMapping("{id}")
    public ResponseEntity<CatsInShelter> deleteCatInShelter(@PathVariable Long id) {
        catsInShelterService.deleteCatInShelter(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Поиск кошки по id или кличке", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Найденная кошка", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
            ), @ApiResponse(
            responseCode = "404", description = "Если кошка не найдена"
    )
    })
    @GetMapping
    public ResponseEntity findCatsInShelter(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) String name) {
        if (id != null) {
            return ResponseEntity.ok(catsInShelterService.findCatInShelterById(id));
        }
        if (name != null) {
            return ResponseEntity.ok(catsInShelterService.findCatInShelterByName(name));
        }
        return ResponseEntity.ok(catsInShelterService.getAllCatsInShelter());
    }
}
