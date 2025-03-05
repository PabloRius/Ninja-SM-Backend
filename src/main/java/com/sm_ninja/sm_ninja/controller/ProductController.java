package com.sm_ninja.sm_ninja.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sm_ninja.sm_ninja.model.PagedResponse;
import com.sm_ninja.sm_ninja.model.Product;

import io.github.cdimascio.dotenv.Dotenv;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Dotenv dotenv = Dotenv.load();

    @GetMapping
    public PagedResponse<Product> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String supermarket,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int limit) {
        List<Product> products = new ArrayList<>();
        int offset = (page - 1) * limit; // Calculate OFFSET for pagination
        int totalItems = 0;

        String baseQuery = " FROM Products WHERE 1=1";
        if (name != null) baseQuery += " AND LOWER(name) LIKE LOWER(?)";
        if (supermarket != null) baseQuery += " AND LOWER(supermarket) LIKE LOWER(?)";
        if (minPrice != null) baseQuery += " AND price >= ?";
        if (maxPrice != null) baseQuery += " AND price <= ?";

        String countQuery = "SELECT COUNT(*)" + baseQuery;
        String dataQuery = "SELECT *" + baseQuery + " ORDER BY name ASC LIMIT ? OFFSET ?";

        String jdbcUrl = dotenv.get("POSTGRESQL_URL");
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            // Get total item count
            try (PreparedStatement countStmt = connection.prepareStatement(countQuery)) {
                setQueryParameters(countStmt, name, supermarket, minPrice, maxPrice);
                ResultSet countResult = countStmt.executeQuery();
                if (countResult.next()) {
                    totalItems = countResult.getInt(1);
                }
            }

            // Get paginated results
            try (PreparedStatement statement = connection.prepareStatement(dataQuery)) {
                int index = setQueryParameters(statement, name, supermarket, minPrice, maxPrice);
                statement.setInt(index++, limit);
                statement.setInt(index, offset);

                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    products.add(new Product(
                            result.getString("name"),
                            result.getDouble("price"),
                            result.getDouble("price_relative"),
                            result.getString("units"),
                            result.getString("supermarket"),
                            result.getString("link"),
                            result.getString("img")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new PagedResponse<>(products, page, limit, totalItems);
    }

    /**
     * Sets query parameters dynamically for filtering.
     */
    private int setQueryParameters(PreparedStatement statement, String name, String supermarket,
                                   Double minPrice, Double maxPrice) throws SQLException {
        int index = 1;
        if (name != null) statement.setString(index++, "%" + name + "%");
        if (supermarket != null) statement.setString(index++, "%" + supermarket + "%");
        if (minPrice != null) statement.setDouble(index++, minPrice);
        if (maxPrice != null) statement.setDouble(index++, maxPrice);
        return index;
    }
}
