package com.example.TD5_springBoot.service;

import com.example.TD5_springBoot.dto.IngredientDTO;
import com.example.TD5_springBoot.entity.Ingredient;
import com.example.TD5_springBoot.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository repo;

    public IngredientService(IngredientRepository repo) {
        this.repo = repo;
    }


    public List<IngredientDTO> getAll() throws SQLException {

        List<Ingredient> ingredients = repo.findAll();

        List<IngredientDTO> dtos = new ArrayList<>();

        for (Ingredient ing : ingredients) {
            IngredientDTO dto = new IngredientDTO();

            dto.setId(ing.getId());
            dto.setName(ing.getName());
            dto.setPrice(ing.getPrice());
            dto.setCategory(ing.getCategory());

            dtos.add(dto);
        }
        return dtos;
    }

    public IngredientDTO getById(int id) throws SQLException {
        Ingredient ingredient = repo.findById(id);

        if (ingredient == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ingredient.id=" + id + " is not found"
            );
        }

        IngredientDTO dto = new IngredientDTO();
        dto.setId(ingredient.getId());
        dto.setName(ingredient.getName());
        dto.setPrice(ingredient.getPrice());
        dto.setCategory(ingredient.getCategory());

        return dto;
    }

}