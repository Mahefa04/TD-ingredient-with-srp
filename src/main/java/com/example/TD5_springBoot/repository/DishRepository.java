package com.example.TD5_springBoot.repository;

import com.example.TD5_springBoot.entity.Dish;
import com.example.TD5_springBoot.entity.Ingredient;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DishRepository {

    private final DataSource dataSource;

    public DishRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dish> findAll() throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = "SELECT id, name, selling_price,  from dish";
        PreparedStatement p = conn.prepareStatement(sql);

        ResultSet rs = p.executeQuery();

        while (rs.next()) {
            Dish dish = new Dish();

            dish.setName(rs.getString("name"));


        }
        return null;
    }
}