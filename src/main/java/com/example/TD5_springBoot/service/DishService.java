package com.example.TD5_springBoot.service;

import com.example.TD5_springBoot.dto.DishDTO;
import com.example.TD5_springBoot.dto.IngredientDTO;
import com.example.TD5_springBoot.entity.Dish;
import com.example.TD5_springBoot.entity.Ingredient;
import com.example.TD5_springBoot.repository.DishRepository;
import com.example.TD5_springBoot.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepo;

    public DishService(DishRepository dishRepository, IngredientRepository ingredientRepo) {
        this.dishRepository = dishRepository;
        this.ingredientRepo = ingredientRepo;
    }

    // 🔥 GET /dishes
    public List<DishDTO> getAll() throws SQLException {

        List<Dish> dishes = dishRepository.findAll();

        List<DishDTO> result = new ArrayList<>();

        for (Dish dish : dishes) {
            result.add(toDTO(dish));
        }

        return result;
    }

    // 🔥 Mapping Entity → DTO
    private DishDTO toDTO(Dish dish) {

        DishDTO dto = new DishDTO();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setPrice(dish.getPrice());

        List<IngredientDTO> ingredientsDTO = new ArrayList<>();

        if (dish.getIngredients() != null) {
            for (Ingredient ing : dish.getIngredients()) {

                IngredientDTO ingDTO = new IngredientDTO();
                ingDTO.setId(ing.getId());
                ingDTO.setName(ing.getName());
                ingDTO.setPrice(ing.getPrice());
                ingDTO.setCategory(ing.getCategory());

                ingredientsDTO.add(ingDTO);
            }
        }

        dto.setIngredients(ingredientsDTO);

        return dto;
    }

    public DishDTO updateDishIngredients(int dishId, List<IngredientDTO> ingredients)
            throws SQLException {

        Dish dish = dishRepository.findDishById(dishId);

        if (dish == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Dish.id=" + dishId + " is not found"
            );
        }

        public DishDTO updateDishIngredients(int dishId, List<IngredientDTO> ingredients)
        throws SQLException {

            Dish dish = dishRepository.findDishById(dishId);

            if (dish == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dish.id=" + dishId + " is not found"
                );
            }

            // 🔥 supprimer anciens liens
            dishRepository.removeIngredientsFromDish(dishId);

            for (IngredientDTO dto : ingredients) {

                Ingredient ing = ingredientRepo.findById(dto.getId());

                if (ing != null) { // 🔥 ignorer inconnus
                    dishRepository.addIngredientToDish(dishId, ing.getId());
                }
            }

            Dish updated = dishRepository.findDishById(dishId);

            return toDTO(updated);
        }

        Dish updated = dishRepository.findDishById(dishId);

        return toDTO(updated);
    }
}