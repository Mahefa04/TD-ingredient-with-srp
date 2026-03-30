package com.example.TD5_springBoot.repository;

import com.example.TD5_springBoot.entity.Ingredient;
import com.example.TD5_springBoot.entity.enums.CategoryEnum;
import org.springframework.format.annotation.DurationFormat;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IngredientRepository {

    private final DataSource dataSource;

    public IngredientRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 🔥 GET ALL INGREDIENTS
    public List<Ingredient> findAll() throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = "SELECT id, name, price, category FROM ingredient ORDER BY id";
        PreparedStatement p = conn.prepareStatement(sql);

        ResultSet rs = p.executeQuery();

        List<Ingredient> list = new ArrayList<>();

        while (rs.next()) {
            Ingredient ing = new Ingredient();

            ing.setId(rs.getInt("id"));
            ing.setName(rs.getString("name"));

            // 🔥 gestion BigDecimal → Double
            Double price = rs.getObject("price") != null
                    ? rs.getBigDecimal("price").doubleValue()
                    : null;
            ing.setPrice(price);

            // 🔥 gestion enum
            String cat = rs.getString("category");
            if (cat != null) {
                ing.setCategory(CategoryEnum.valueOf(cat.toUpperCase()));
            }

            list.add(ing);
        }

        return list;
    }

    // 🔥 GET INGREDIENT BY ID
    public Ingredient findById(int id) throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = "SELECT id, name, price, category FROM ingredient WHERE id = ?";
        PreparedStatement p = conn.prepareStatement(sql);
        p.setInt(1, id);

        ResultSet rs = p.executeQuery();

        if (rs.next()) {
            Ingredient ing = new Ingredient();

            ing.setId(rs.getInt("id"));
            ing.setName(rs.getString("name"));

            Double price = rs.getObject("price") != null
                    ? rs.getBigDecimal("price").doubleValue()
                    : null;
            ing.setPrice(price);

            String cat = rs.getString("category");
            if (cat != null) {
                ing.setCategory(CategoryEnum.valueOf(cat.toUpperCase()));
            }

            return ing;
        }

        return null;
    }


}