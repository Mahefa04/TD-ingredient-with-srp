package com.example.TD5_springBoot.dto;

import com.example.TD5_springBoot.entity.enums.DishTypeEnum;

import java.util.Objects;

public class DishDTO {
    private Integer id;
    private String name;
    private DishTypeEnum dishType;
    private Double price;

    public DishDTO(Integer id, String name, DishTypeEnum dishType, Double price) {
        this.id = id;
        this.name = name;
        this.dishType = dishType;
        this.price = price;
    }

    public DishDTO() {

    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DishDTO dishDTO = (DishDTO) o;
        return Objects.equals(id, dishDTO.id) && Objects.equals(name, dishDTO.name) && dishType == dishDTO.dishType && Objects.equals(price, dishDTO.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dishType, price);
    }

    @Override
    public String toString() {
        return "DishDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishType=" + dishType +
                ", price=" + price +
                '}';
    }
}
