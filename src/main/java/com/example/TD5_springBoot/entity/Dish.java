package com.example.TD5_springBoot.entity;

import com.example.TD5_springBoot.entity.enums.DishTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class Dish {

    private Integer id;
    private String name;
    private DishTypeEnum dishType;
    private Double price;


    private List<DishIngredient> dishIngredients = new ArrayList<>();


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

    public DishTypeEnum getDishType() {
        return dishType;
    }

    public void setDishType(DishTypeEnum dishType) {
        this.dishType = dishType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<DishIngredient> getDishIngredients() {
        return dishIngredients;
    }

    public void setDishIngredients(List<DishIngredient> dishIngredients) {
        this.dishIngredients = dishIngredients;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishType=" + dishType +
                ", price=" + price +
                ", dishIngredients=" + dishIngredients +
                '}';
    }
}