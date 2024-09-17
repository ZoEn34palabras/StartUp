package org.example.startup.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/desafiostartup";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "pass";

    static {
        try {
            System.out.println("DBConnection: Attempting to load PostgreSQL driver");
            Class.forName("org.postgresql.Driver");
            System.out.println("DBConnection: PostgreSQL driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("DBConnection: Failed to load PostgreSQL driver: " + e.getMessage());
            try {
                throw new SQLException("No se pudo cargar el driver de la base de datos");
            } catch (SQLException ex) {
                System.out.println("DBConnection: SQLException occurred while throwing driver load error: " + ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("DBConnection: Attempting to establish database connection");
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("DBConnection: Connection established successfully");
            return conn;
        } catch (SQLException e) {
            System.out.println("DBConnection: Failed to establish connection: " + e.getMessage());
            throw e;
        }
    }
}


