package com.example.TD5_springBoot.service;

import com.example.TD5_springBoot.dto.DishDTO;
import com.example.TD5_springBoot.entity.Dish;
import com.example.TD5_springBoot.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DishService {

    private final DishRepository repo;

    public DishService(DishRepository repo) {
        this.repo = repo;
    }

    public DishDTO getById(int id) throws SQLException {
        Dish dish = repo.findDishById(id);

        DishDTO dto = new DishDTO();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setDishType(dish.getDishType());
        dto.setPrice(dish.getPrice());

        return dto;
    }
}