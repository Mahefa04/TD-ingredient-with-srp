package com.example.TD5_springBoot.repository;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class StockMovementRepository {

    private final DataSource dataSource;

    public StockMovementRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public double calculateStock(int ingredientId, Timestamp at) throws SQLException {

        Connection conn = dataSource.getConnection();

        String sql = """
        SELECT quantity, type
        FROM stock_movement
        WHERE id_ingredient = ?
        AND creation_datetime <= ?
    """;

        PreparedStatement p = conn.prepareStatement(sql);
        p.setInt(1, ingredientId);
        p.setTimestamp(2, at);

        ResultSet rs = p.executeQuery();

        double stock = 0;

        while (rs.next()) {
            double qty = rs.getDouble("quantity");
            String type = rs.getString("type");

            if ("IN".equals(type)) {
                stock += qty;
            } else {
                stock -= qty;
            }
        }

        return stock;
    }
}
