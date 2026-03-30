package com.example.TD5_springBoot.controller;

import com.example.TD5_springBoot.dto.IngredientDTO;
import com.example.TD5_springBoot.entity.Ingredient;
import com.example.TD5_springBoot.service.IngredientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @GetMapping("/ingredients")
    public List<IngredientDTO> getAllIngredients() throws SQLException {
        return service.getAll();
    }

    @GetMapping("/ingredients/{id}")
    public IngredientDTO getById(@PathVariable int id) throws SQLException {
        return service.getById(id);
    }
}
