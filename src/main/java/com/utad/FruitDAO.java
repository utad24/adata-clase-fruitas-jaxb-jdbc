package com.utad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FruitDAO {
    private static final String TABLE_NAME = "adat_fruits";
    private static Connection connection;

    public FruitDAO() {
        connection = DBConnection.getConnection();
    }

    public void createFruit(String name, String color, String weight) {
        try {
            String query = "INSERT INTO " + TABLE_NAME + " (name, color, weight) VALUES (?,?,?) RETURNING id;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, color);
            preparedStatement.setString(3, weight);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Fruta añadida: " + resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Error al crear fruta." + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
