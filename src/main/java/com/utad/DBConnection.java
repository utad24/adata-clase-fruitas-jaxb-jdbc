package com.utad;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "postgres";

    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            String createTable = "CREATE TABLE IF NOT EXISTS adat_fruits (id SERIAL PRIMARY KEY, name VARCHAR(255), color VARCHAR(255), weight VARCHAR(255));";
            Statement statement = conn.createStatement();
            statement.executeUpdate(createTable);

            String checkTable = "SELECT to_regclass('public.adat_fruits');";
            ResultSet rs = statement.executeQuery(checkTable);

            if(rs.next() && rs.getString(1) != null) {
                System.out.println("Conectado a la tabla correctamente: " + rs.getString(1));
            } else {
                System.out.println("Error al crear la tabla.");
            }

            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
