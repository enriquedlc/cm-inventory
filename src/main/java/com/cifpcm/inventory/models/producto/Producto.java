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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEAN13(String EAN13) {
        this.EAN13 = EAN13;
    }

    public void setKeyRFID(String keyRFID) {
        this.keyRFID = keyRFID;
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

}
