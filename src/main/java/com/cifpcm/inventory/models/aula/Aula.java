package com.cifpcm.inventory.models.aula;

import com.cifpcm.inventory.database.ConnectionDb;
import com.cifpcm.inventory.database.SQLBuilder;
import com.cifpcm.inventory.models.marcaje.Marcaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Aula implements AulaInterface {
    private int idAula;
    private  String numeracion;
    private String descripcion;
    private String ip;
    
    public Aula(String numeracion, String descripcion, String ip) {
        this.numeracion = numeracion;
        this.descripcion = descripcion;
        this.ip = ip;
    }
    
    public Aula(int idAula, String numeracion, String descripcion, String ip) {
        this.idAula = idAula;
        this.numeracion = numeracion;
        this.descripcion = descripcion;
        this.ip = ip;
    }

    public Aula() {}

    @Override
    public boolean insertAula(Aula aula) {
        String sql = SQLBuilder.getINSERT_AULA_SQL();
        boolean rowInserted = false;
        try (Connection connection = ConnectionDb.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, aula.getNumeracion());
            preparedStatement.setString(2, aula.getDescripcion());
            preparedStatement.setString(3, aula.getIp());
            rowInserted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rowInserted;
    }

    @Override
    public boolean updateAula(Aula aula) {
        String sql = SQLBuilder.getUPDATE_AULA_SQL();
        boolean rowUpdated = false;
        try (Connection connection = ConnectionDb.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, aula.getNumeracion());
            preparedStatement.setString(2, aula.getDescripcion());
            preparedStatement.setString(3, aula.getIp());
            preparedStatement.setInt(4, aula.getIdAula());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteAula(int idAula) {
    Marcaje marcaje = new Marcaje();
    int count = marcaje.countMarcajesByAula(idAula);

    if (count > 0) {
        System.out.println("No se puede eliminar el aula con ID " + idAula + " porque tiene marcajes asociados.");
        return false;
    }

    String sql = SQLBuilder.getDELETE_AULA_SQL();
    boolean rowDeleted = false;
    try (Connection connection = ConnectionDb.get();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, idAula);
        rowDeleted = preparedStatement.executeUpdate() > 0;
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return rowDeleted;
    }

    @Override
    public Aula selectAula(int id) {
        String sql = SQLBuilder.getSELECT_AULA_BY_ID();
        Aula aula = null;
        try (Connection connection = ConnectionDb.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                String numeracion = result.getString("numeracion");
                String descripcion = result.getString("descripcion");
                String ip = result.getString("ip");
                aula = new Aula(idAula, numeracion, descripcion, ip);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return aula;
    }

    @Override
    public ArrayList<Aula> selectAllAulas() {
        String sql = SQLBuilder.getSELECT_ALL_AULAS();
        ArrayList<Aula> aulas = new ArrayList<>();
        try (Connection connection = ConnectionDb.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet result = preparedStatement.executeQuery()) {
            while (result.next()) {
                int idAula = result.getInt("idAula");
                String numeracion = result.getString("numeracion");
                String descripcion = result.getString("descripcion");
                String ip = result.getString("ip");
                aulas.add(new Aula(idAula, numeracion, descripcion, ip));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return aulas;
    }
    public boolean exists(int idAula) {
    String sql = SQLBuilder.getEXISTS_AULA_SQL();
    try (Connection connection = ConnectionDb.get();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, idAula);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return false;
}

    @Override
    public int getIdAula() {
        return idAula;
    }

    @Override
    public String getNumeracion() {
        return numeracion;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "idAula=" + idAula +
                ", numeracion='" + numeracion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

}

