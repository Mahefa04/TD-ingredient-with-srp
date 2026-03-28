package com.example.TD5_springBoot.entity;


import com.example.TD5_springBoot.entity.enums.UnitTypeEnum;

public class DishIngredient {
    private Dish dish;
    private Ingredient ingredient;
    private Double quantity;
    private UnitTypeEnum unit;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public UnitTypeEnum getUnit() {
        return unit;
    }

    public void setUnit(UnitTypeEnum unit) {
        this.unit = unit;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "DishIngredient{" +
                "ingredient=" + ingredient +
                ", quantity=" + quantity +
                ", unit=" + unit +
                '}';
    }
}
