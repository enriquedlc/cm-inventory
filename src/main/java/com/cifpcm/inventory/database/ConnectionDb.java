package com.cifpcm.inventory.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/difpcm-inventory";
        String user = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
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




