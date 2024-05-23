package com.cifpcm.inventory.models.marcaje;

import com.cifpcm.inventory.database.ConnectionDb;
import com.cifpcm.inventory.database.SQLBuilder;
import com.cifpcm.inventory.models.aula.Aula;
import com.cifpcm.inventory.models.producto.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Marcaje implements MarcajeInterface {

    private int idProducto;
    private int idAula;
    private Date timeStamp;
    private TipoMarcaje tipo;
    private int idMarcaje;

    public Marcaje() {
    }

    public Marcaje(int idProducto, int idAula, Date timeStamp, TipoMarcaje tipo) {
        this.idProducto = idProducto;
        this.idAula = idAula;
        this.timeStamp = timeStamp;
        this.tipo = tipo;
    }

    public Marcaje(int idMarcaje, int idProducto, int idAula, Date timeStamp, TipoMarcaje tipo) {
        this.idMarcaje = idMarcaje;
        this.idProducto = idProducto;
        this.idAula = idAula;
        this.timeStamp = timeStamp;
        this.tipo = tipo;
    }

    @Override
    public int getIdMarcaje() {
        return idMarcaje;
    }

    // Getter and Setter for idMarcaje
    public void setIdMarcaje(int idMarcaje) {
        this.idMarcaje = idMarcaje;
    }

    @Override
    public int getIdProducto() {
        return idProducto;
    }

    @Override
    public int getIdAula() {
        return idAula;
    }

    @Override
    public Date getTimeStamp() {
        return timeStamp;
    }

    @Override
    public TipoMarcaje getTipo() {
        return tipo;
    }
    

    @Override
    public boolean insertMarcaje(MarcajeInterface marcaje) {
        Aula aula = new Aula();
    Producto producto = new Producto();

    if (!aula.exists(marcaje.getIdAula())) {
        System.out.println("El aula con ID " + marcaje.getIdAula() + " no existe.");
        return false;
    }

    if (!producto.exists(marcaje.getIdProducto())) {
        System.out.println("El producto con ID " + marcaje.getIdProducto() + " no existe.");
        return false;
    }

    String sql = SQLBuilder.getINSERT_MARCAJE_SQL();
    boolean rowInserted = false;
    try (Connection connection = ConnectionDb.get();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, marcaje.getIdProducto());
        preparedStatement.setInt(2, marcaje.getIdAula());
        preparedStatement.setTimestamp(3, new java.sql.Timestamp(marcaje.getTimeStamp().getTime()));
        preparedStatement.setInt(4, marcaje.getTipo().ordinal());
        rowInserted = preparedStatement.executeUpdate() > 0;
    } catch (SQLException ex) {
        System.out.println(ex);
    }
    return rowInserted;

    }

    @Override
    public boolean updateMarcaje(MarcajeInterface marcaje) {
        String sql = SQLBuilder.getUPDATE_MARCAJE_SQL();
        boolean rowUpdated = false;
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, marcaje.getIdProducto());
            preparedStatement.setInt(2, marcaje.getIdAula());
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(marcaje.getTimeStamp().getTime()));
            preparedStatement.setString(4, marcaje.getTipo().toString());
            preparedStatement.setInt(5, marcaje.getIdMarcaje());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteMarcaje(int id) {
        String sql = SQLBuilder.getDELETE_MARCAJE_SQL();
        boolean rowDeleted = false;
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idMarcaje);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rowDeleted;
    }

    @Override
    public MarcajeInterface selectMarcaje(int id) {
        String sql = SQLBuilder.getSELECT_MARCAJE_BY_ID();
        Marcaje marcaje = null;
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idMarcaje);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int idProducto = result.getInt("idProducto");
                int idAula = result.getInt("idAula");
                Date timeStamp = result.getTimestamp("timeStamp");
                TipoMarcaje tipo = TipoMarcaje.valueOf(result.getString("tipo"));
                marcaje = new Marcaje(idMarcaje, idProducto, idAula, timeStamp, tipo);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return marcaje;
    }

    @Override
    public ArrayList<MarcajeInterface> selectAllMarcajes() {
        String sql = SQLBuilder.getSELECT_ALL_MARCAJES();
        ArrayList<MarcajeInterface> marcajes = new ArrayList<>();
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet result = preparedStatement.executeQuery()) {
            while (result.next()) {
                int idMarcaje = result.getInt("idMarcaje");
                int idProducto = result.getInt("idProducto");
                int idAula = result.getInt("idAula");
                Date timeStamp = result.getTimestamp("timeStamp");
                int tipoValue = result.getInt("tipo");
                TipoMarcaje tipo;
                if (tipoValue == 0) {
                    tipo = TipoMarcaje.ENTRADA;
                } else {
                    tipo = TipoMarcaje.SALIDA;
                }
                marcajes.add(new Marcaje(idMarcaje, idProducto, idAula, timeStamp, tipo));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return marcajes;
    }

    public boolean deleteMarcajesByProducto(int idProducto) {
        String sql = SQLBuilder.getDELETE_MARCAJES_BY_PRODUCTO_SQL();
        boolean rowsDeleted = false;
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idProducto);
            rowsDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rowsDeleted;
    }
    
    public int countMarcajesByAula(int idAula) {
        String sql = "SELECT COUNT(*) FROM marcaje WHERE idAula = ?";
        int count = 0;
        try (Connection connection = ConnectionDb.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idAula);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }

    @Override
    public String toString() {
        return "Marcaje{" +
                "idMarcaje=" + idMarcaje +
                ", idProducto=" + idProducto +
                ", idAula=" + idAula +
                ", timeStamp=" + timeStamp +
                ", tipo=" + tipo +
                '}';
    }
}
