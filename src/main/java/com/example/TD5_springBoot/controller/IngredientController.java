package com.example.TD5_springBoot.controller;

import com.example.TD5_springBoot.dto.IngredientDTO;
import com.example.TD5_springBoot.dto.StockResponseDTO;
import com.example.TD5_springBoot.service.IngredientService;
import com.example.TD5_springBoot.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class IngredientController {

    private final IngredientService service;
    private final StockService stockService;

    public IngredientController(IngredientService service, StockService stockService) {
        this.service = service;
        this.stockService = stockService;
    }

    @GetMapping("/ingredients")
    public List<IngredientDTO> getAllIngredients() throws SQLException {
        return service.getAll();
    }

    @GetMapping("/ingredients/{id}")
    public IngredientDTO getById(@PathVariable int id) throws SQLException {
        return service.getById(id);
    }

    @GetMapping("/ingredients/{id}/stock")
    public StockResponseDTO getStock(
            @PathVariable int id,
            @RequestParam String at,
            @RequestParam String unit
    ) throws SQLException {

        return stockService.getStock(id, at, unit);
    }
}
