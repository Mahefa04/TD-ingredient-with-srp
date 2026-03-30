package com.example.TD5_springBoot.repository;

import com.example.TD5_springBoot.entity.Dish;
import com.example.TD5_springBoot.entity.enums.DishTypeEnum;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class DishRepository {

    private final DataSource dataSource;

    public DishRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Dish findDishById(int id) throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = "SELECT id, name, dish_type, selling_price FROM dish WHERE id = ?";
        PreparedStatement p = conn.prepareStatement(sql);
        p.setInt(1, id);

        ResultSet rs = p.executeQuery();

        if (rs.next()) {
            Dish dish = new Dish();
            dish.setId(rs.getInt("id"));
            dish.setName(rs.getString("name"));

            String type = rs.getString("dish_type");
            if (type != null) {
                dish.setDishType(DishTypeEnum.valueOf(type.toUpperCase()));
            }

            // 🔥 CORRECTION ICI
            Double price = rs.getObject("selling_price") != null
                    ? rs.getBigDecimal("selling_price").doubleValue()
                    : null;
            dish.setPrice(price);

            return dish;

        }

        return null;

    }
}