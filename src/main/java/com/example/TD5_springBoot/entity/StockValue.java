package com.example.TD5_springBoot.entity;

import com.example.TD5_springBoot.entity.enums.UnitTypeEnum;

public class StockValue {
    private Double quantity;
    private UnitTypeEnum unit;

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

    @Override
    public String toString() {
        return "StockValue{" +
                "quantity=" + quantity +
                ", unit=" + unit +
                '}';
    }
}
