package com.example.TD5_springBoot.controller;

import com.example.TD5_springBoot.dto.DishDTO;
import com.example.TD5_springBoot.service.DishService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class DishController {

    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @GetMapping("/dishes/{id}")
    public DishDTO getDish(@PathVariable int id) throws SQLException {
        return service.getById(id);
    }
}