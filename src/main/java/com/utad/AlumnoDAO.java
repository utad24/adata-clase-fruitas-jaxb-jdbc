package com.utad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {
    private static final String TABLE_NAME = "adat_alumnos";
    private static Connection connection;

    public AlumnoDAO() {
        connection = DBConnection.getConnection();
    }

    public void createAlumno(String name) {
        try {
            String query = "INSERT INTO " + TABLE_NAME + " (name) VALUES (?) RETURNING id;";

            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Alumno creado con ID: " + resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Error al crear el alumno." + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
