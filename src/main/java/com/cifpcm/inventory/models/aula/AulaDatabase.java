package com.cifpcm.inventory.models.aula;

import com.cifpcm.inventory.database.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AulaDatabase implements AulaRepository {
    private static final String INSERT_QUERY = "INSERT INTO aula (numeracion, descripcion, direccionIp) VALUES (?, ?, ?)";

    @Override
    public boolean create(Aula aula) {
        try {
            Connection connection = ConnectionDb.get();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);

            preparedStatement.setString(1, aula.getNumeracion());
            preparedStatement.setString(2, aula.getDescripcion());
            preparedStatement.setString(3, aula.getDireccionIp());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Aula read(int idAula) {
        try {
            Connection connection = ConnectionDb.get();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM aula WHERE idAula = ?");
            preparedStatement.setInt(1, idAula);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("idAula");
                String numeracion = resultSet.getString("numeracion");
                String descripcion = resultSet.getString("descripcion");
                String ip = resultSet.getString("ip");

                return new Aula(id, numeracion, descripcion, ip);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public boolean update(Aula aula) {
        try {
            Connection connection = ConnectionDb.get();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE aula SET numeracion = ?, descripcion = ?, direccionIp = ? WHERE idAula = ?");

            preparedStatement.setString(1, aula.getNumeracion());
            preparedStatement.setString(2, aula.getDescripcion());
            preparedStatement.setString(3, aula.getDireccionIp());
            preparedStatement.setInt(4, aula.getIdAula());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Aula aula) {
        try {
            Connection connection = ConnectionDb.get();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM aula WHERE idAula = ?");
            preparedStatement.setInt(1, aula.getIdAula());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
