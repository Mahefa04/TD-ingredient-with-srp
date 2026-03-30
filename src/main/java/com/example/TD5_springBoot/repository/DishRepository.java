package com.example.TD5_springBoot.repository;

import com.example.TD5_springBoot.entity.Dish;
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

    public List<String> findDish() throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = "SELECT name from dish";
        PreparedStatement p = conn.prepareStatement(sql);

        ResultSet rs = p.executeQuery();

        if (rs.next()) {
            List<String> dish = new ArrayList<>();
            dish.add(rs.getString("name"));

            return dish;
        }
        return null;
    }
}