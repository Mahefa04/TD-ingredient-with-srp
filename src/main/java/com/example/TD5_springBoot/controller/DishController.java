package com.example.TD5_springBoot.controller;

import com.example.TD5_springBoot.service.DishService;
import org.springframework.web.bind.annotation.*;


@RestController
public class DishController {
    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }
}