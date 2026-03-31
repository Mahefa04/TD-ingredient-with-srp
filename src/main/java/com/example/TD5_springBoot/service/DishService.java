package com.example.TD5_springBoot.service;

import com.example.TD5_springBoot.dto.DishDTO;
import com.example.TD5_springBoot.dto.IngredientDTO;
import com.example.TD5_springBoot.entity.Dish;
import com.example.TD5_springBoot.entity.Ingredient;
import com.example.TD5_springBoot.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {

    private final DishRepository repo;

    public DishService(DishRepository repo) {
        this.repo = repo;
    }
}