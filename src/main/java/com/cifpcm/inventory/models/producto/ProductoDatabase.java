package com.cifpcm.inventory.models.producto;

import com.cifpcm.inventory.database.ConnectionDb;
import com.cifpcm.inventory.database.SQLBuilder;

import java.sql.*;
import java.util.ArrayList;

public class Producto implements ProductoInterface {

    private String descripcion;
    private String EAN13;
    private String keyRFID;
    private int idProducto;

    public Producto() {
    }

    public Producto(int idProducto, String descripcion, String EAN13, String keyRFID) {
        this.idProducto = idProducto;
        this.descripcion = descripcion;
        this.EAN13 = EAN13;
        this.keyRFID = keyRFID;
    }

    public Producto(String descripcion, String EAN13, String keyRFID) {
        this.descripcion = descripcion;
        this.EAN13 = EAN13;
        this.keyRFID = keyRFID;
    }

    @Override
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String getEan() {
        return EAN13;
    }

    @Override
    public String getKeyRFID() {
        return keyRFID;
    }

    @Override
    public String toString() {
        return "Producto{" + "descripcion=" + descripcion + ", EAN13=" + EAN13 + ", keyRFID=" + keyRFID + ", idProducto=" + idProducto + '}';
    }

    @Override
    public boolean insertProducto(Producto producto) {
        String sql = SQLBuilder.getINSERT_PRODUCTO_SQL();
        boolean rowInserted = false;
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setString(2, producto.getEan());
            preparedStatement.setString(3, producto.getKeyRFID());
            rowInserted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rowInserted;
    }

    @Override
    public boolean updateProducto(Producto producto) {
        String sql = SQLBuilder.getUPDATE_PRODUCTO_SQL();
        boolean rowUpdated = false;
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, producto.getDescripcion());
            preparedStatement.setString(2, producto.getEan());
            preparedStatement.setString(3, producto.getKeyRFID());
            preparedStatement.setInt(4, producto.getIdProducto());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteProducto(int id) {
        String sql = SQLBuilder.getDELETE_PRODUCTO_SQL();
        boolean rowDeleted = false;
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return rowDeleted;
    }

    @Override
    public Producto selectProducto(int id) {
        String sql = SQLBuilder.getSELECT_PRODUCTO_BY_ID();
        Producto producto = null;
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                String descripcion = result.getString("descripcion");
                String EAN13 = result.getString("EAN13");
                String keyRFID = result.getString("keyRFID");
                producto = new Producto(id, descripcion, EAN13, keyRFID);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return producto;
    }

    @Override
    public ArrayList<Producto> selectAllProductos() {
        String sql = SQLBuilder.getSELECT_ALL_PRODUCTOS();
        ArrayList<Producto> productos = new ArrayList<>();
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet result = preparedStatement.executeQuery()) {
            while (result.next()) {
                int idProducto = result.getInt("idProducto");
                String descripcion = result.getString("descripcion");
                String EAN13 = result.getString("EAN13");
                String keyRFID = result.getString("keyRFID");
                productos.add(new Producto(idProducto, descripcion, EAN13, keyRFID));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return productos;
    }

    public boolean exists(int idProducto) {
        String sql = SQLBuilder.getEXISTS_PRODUCTO_SQL();
        try (Connection connection = ConnectionDb.get(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idProducto);
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

}
