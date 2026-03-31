package com.example.TD5_springBoot.repository;

import com.example.TD5_springBoot.dto.IngredientDTO;
import com.example.TD5_springBoot.entity.Dish;
import com.example.TD5_springBoot.entity.Ingredient;
import com.example.TD5_springBoot.entity.enums.CategoryEnum;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DishRepository {

    private final DataSource dataSource;

    public DishRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dish> findAll() throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = """
        SELECT d.id AS dish_id, d.name AS dish_name, d.selling_price,
               i.id AS ingredient_id, i.name AS ingredient_name,
               i.price AS ingredient_price, i.category
        FROM dish d
        LEFT JOIN ingredient i ON d.id = i.id_dish
        ORDER BY d.id
    """;

        PreparedStatement p = conn.prepareStatement(sql);
        ResultSet rs = p.executeQuery();

        Map<Integer, Dish> map = new HashMap<>();

        while (rs.next()) {

            int dishId = rs.getInt("dish_id");

            Dish dish = map.get(dishId);

            if (dish == null) {
                dish = new Dish();
                dish.setId(dishId);
                dish.setName(rs.getString("dish_name"));

                Double price = rs.getObject("selling_price") != null
                        ? rs.getBigDecimal("selling_price").doubleValue()
                        : null;

                dish.setPrice(price);
                dish.setIngredients(new ArrayList<>());

                map.put(dishId, dish);
            }

            int ingId = rs.getInt("ingredient_id");

            if (ingId != 0) {
                Ingredient ing = new Ingredient();
                ing.setId(ingId);
                ing.setName(rs.getString("ingredient_name"));
                ing.setPrice(rs.getDouble("ingredient_price"));
                ing.setCategory(
                        CategoryEnum.valueOf(rs.getString("category"))
                );

                dish.getIngredients().add(ing);
            }
        }

        return new ArrayList<>(map.values());
    }

    public void removeIngredientsFromDish(int dishId) throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = "UPDATE ingredient SET id_dish = NULL WHERE id_dish = ?";
        PreparedStatement p = conn.prepareStatement(sql);

        p.setInt(1, dishId);
        p.executeUpdate();
    }

    public void addIngredientToDish(int dishId, int ingredientId) throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = "UPDATE ingredient SET id_dish = ? WHERE id = ?";
        PreparedStatement p = conn.prepareStatement(sql);

        p.setInt(1, dishId);
        p.setInt(2, ingredientId);

        p.executeUpdate();
    }

    public Dish findDishById(int id) throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = "SELECT id, name, selling_price FROM dish WHERE id = ?";
        PreparedStatement p = conn.prepareStatement(sql);
        p.setInt(1, id);

        ResultSet rs = p.executeQuery();

        if (rs.next()) {
            Dish dish = new Dish();

            dish.setId(rs.getInt("id"));
            dish.setName(rs.getString("name"));

            Double price = rs.getObject("selling_price") != null
                    ? rs.getBigDecimal("selling_price").doubleValue()
                    : null;

            dish.setPrice(price);

            dish.setIngredients(new ArrayList<>()); // important

            return dish;
        }

        return null;
    }public List<Ingredient> findIngredientsByDishWithFilter(
            int dishId,
            String name,
            Double price
    ) throws SQLException {

        Connection conn = dataSource.getConnection();

        StringBuilder sql = new StringBuilder("""
        SELECT id, name, price, category
        FROM ingredient
        WHERE id_dish = ?
    """);

        // 🔥 filtre nom
        if (name != null) {
            sql.append(" AND LOWER(name) LIKE LOWER(?)");
        }

        // 🔥 filtre prix ±50
        if (price != null) {
            sql.append(" AND price BETWEEN ? AND ?");
        }

        PreparedStatement p = conn.prepareStatement(sql.toString());

        int index = 1;
        p.setInt(index++, dishId);

        if (name != null) {
            p.setString(index++, "%" + name + "%");
        }

        if (price != null) {
            p.setDouble(index++, price - 50);
            p.setDouble(index++, price + 50);
        }

        ResultSet rs = p.executeQuery();

        List<Ingredient> list = new ArrayList<>();

        while (rs.next()) {

            Ingredient ing = new Ingredient();

            ing.setId(rs.getInt("id"));
            ing.setName(rs.getString("name"));
            ing.setPrice(rs.getDouble("price"));
            ing.setCategory(
                    CategoryEnum.valueOf(rs.getString("category"))
            );

            list.add(ing);
        }

        return list;
    }


}