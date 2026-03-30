package com.example.TD5_springBoot.service;

import com.example.TD5_springBoot.dto.StockResponseDTO;
import com.example.TD5_springBoot.repository.StockMovementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.sql.Timestamp;

@Service
public class StockService {

    private final StockMovementRepository repo;

    public StockService(StockMovementRepository repo) {
        this.repo = repo;
    }

    public StockResponseDTO getStock(int id, String at, String unit) throws SQLException {

        // 🔥 validation paramètres
        if (at == null || unit == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Either mandatory query parameter `at` or `unit` is not provided."
            );
        }

        Timestamp timestamp = Timestamp.valueOf(at);

        double stock = repo.calculateStock(id, timestamp);

        StockResponseDTO dto = new StockResponseDTO();
        dto.setQuantity(stock);
        dto.setUnit(unit);

        return dto;
    }
}
