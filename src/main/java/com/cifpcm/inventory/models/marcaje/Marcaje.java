package com.cifpcm.inventory.models.marcaje;

import java.util.Date;


public class Marcaje implements MarcajeInterface {
    private int idProducto;
    private int idAula;
    private Date timeStamp;
    private TipoMarcaje tipo;
    private int idMarcaje;
    private String descripcionProducto;

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

    public Marcaje(int idProducto, int idAula, Date timeStamp, TipoMarcaje tipo, int idMarcaje, String descripcionProducto) {
        this.idProducto = idProducto;
        this.idAula = idAula;
        this.timeStamp = timeStamp;
        this.tipo = tipo;
        this.idMarcaje = idMarcaje;
        this.descripcionProducto = descripcionProducto;
    }

    @Override
    public int getIdMarcaje() {
        return idMarcaje;
    }

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

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
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
