package com.cifpcm.inventory.models;

import java.sql.Timestamp;

public class Marcaje{

    private String marcaje;
    private int idProducto;
    private int idAula;
    TipoMarcaje tipoMarcaje;
    Timestamp timestamp;

    public Marcaje() {
    }

    public Marcaje(String marcaje, int idProducto, int idAula, TipoMarcaje tipoMarcaje, Timestamp timestamp) {
        this.marcaje = marcaje;
        this.idProducto = idProducto;
        this.idAula = idAula;
        this.tipoMarcaje = tipoMarcaje;
        this.timestamp = timestamp;
    }

    public String getMarcaje() {
        return marcaje;
    }

    public void setMarcaje(String marcaje) {
        this.marcaje = marcaje;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public TipoMarcaje getTipoMarcaje() {
        return tipoMarcaje;
    }

    public void setTipoMarcaje(TipoMarcaje tipoMarcaje) {
        this.tipoMarcaje = tipoMarcaje;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Marcaje{" + "marcaje=" + marcaje + ", idProducto=" + idProducto + ", idAula=" + idAula + ", tipoMarcaje=" + tipoMarcaje + ", timestamp=" + timestamp + '}';
    }
    
}
