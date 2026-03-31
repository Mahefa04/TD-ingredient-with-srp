package com.example.TD5_springBoot.dto;

import java.util.List;

public class DishDTO {

    private Integer id;
    private String name;
    private Double price;

    // 🔥 liste des ingrédients
    private List<IngredientDTO> ingredients;

    // Getter / Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}