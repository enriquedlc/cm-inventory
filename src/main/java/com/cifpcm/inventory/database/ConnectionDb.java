package com.cifpcm.inventory.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb {

    private static final String URL = "jdbc:mysql://localhost:3306/inventario";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM aula" + " ");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)
                        + "\t" + resultSet.getString(2)
                        + "\t" + resultSet.getString(3) + "\t"
                        + "Conexión exitosa");
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}




