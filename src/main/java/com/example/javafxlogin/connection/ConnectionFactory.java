package com.example.javafxlogin.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/login";
        String userName = "root";
        String password = "admin";

        try {
            return DriverManager.getConnection(url,  userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
