package com.cifpcm.inventory.models.producto;

public class Producto{

    private String idProducto;
    private String Descripcion;
    private String EAN13;
    private String keyRFID;

    public Producto() {
    }

    public Producto(String idProducto, String Descripcion, String EAN13, String keyRFID) {
        this.idProducto = idProducto;
        this.Descripcion = Descripcion;
        this.EAN13 = EAN13;
        this.keyRFID = keyRFID;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getEAN13() {
        return EAN13;
    }

    public void setEAN13(String EAN13) {
        this.EAN13 = EAN13;
    }

    public String getKeyRFID() {
        return keyRFID;
    }

    public void setKeyRFID(String keyRFID) {
        this.keyRFID = keyRFID;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", Descripcion=" + Descripcion + ", EAN13=" + EAN13 + ", keyRFID=" + keyRFID + '}';
    }
    
}
