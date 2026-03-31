package com.example.TD5_springBoot.controller;

import com.example.TD5_springBoot.dto.DishDTO;
import com.example.TD5_springBoot.dto.IngredientDTO;
import com.example.TD5_springBoot.service.DishService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;


@RestController
public class DishController {
    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @GetMapping("/dishes")
    public List<DishDTO> getAllDishes() throws SQLException {
        return service.getAll();
    }

    @PutMapping("/dishes/{id}/ingredients")
    public DishDTO updateDishIngredients(
            @PathVariable int id,
            @RequestBody List<IngredientDTO> ingredients
    ) throws SQLException {

        if (ingredients == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Request body is required"
            );
        }

        return service.updateDishIngredients(id, ingredients);
    }
}