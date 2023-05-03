package com.example.javateambot.controllers;

import com.example.javateambot.entity.Users;
import com.example.javateambot.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {


    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Operation(summary = "Добавление нового владельца",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Новый владелец", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)),
            responses = {@ApiResponse(responseCode = "200",
                    description = "Владелец успешно добавлено")}
    )
    @PostMapping
    public Users addUser(@RequestBody Users user) {
        return usersService.addUser(user);
    }

    @Operation(summary = "Изменение параметров владельца",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Новый владелец",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ))
    @PutMapping
    public ResponseEntity<Users> editUser(@RequestBody Users user) {
        Users foundUser = usersService.editUser(user);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundUser);
    }

    @Operation(summary = "Удаление владельца по id",
            responses = {@ApiResponse(
                    responseCode = "200", description = "Владелец успешно удален",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
                    @ApiResponse(responseCode = "404",
                            description = "Если владелец не найден"
                    )}
    )
    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Поиск владельца по id",
            responses = {@ApiResponse(
                    responseCode = "200", description = "Найденный владелец",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
            ),
                    @ApiResponse(responseCode = "404",
                            description = "Если владелец не найден"
                    )}
    )
    @GetMapping
    public ResponseEntity findUser(@RequestParam(required = false) Long id) {
        if (id != null) {
            return ResponseEntity.ok(usersService.findUsersById(id));
        }
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @RequestMapping("/volrep/{id}")
    public void sendM(@PathVariable Long id){
        usersService.sendM(id);
    }
}
