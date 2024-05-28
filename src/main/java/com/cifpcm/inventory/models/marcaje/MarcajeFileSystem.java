package com.cifpcm.inventory.models.marcaje;

import com.cifpcm.inventory.mediator.Mediator;
import com.cifpcm.inventory.models.marcaje.enums.TipoMarcaje;

import java.util.ArrayList;
import java.util.Date;

public class MarcajeFileSystem implements MarcajeInterface {
    private int idProducto;
    private int idAula;
    private Date timeStamp;
    private TipoMarcaje tipo;
    private int idMarcaje;
    private Mediator mediator;

    public MarcajeFileSystem(Mediator mediator) {
        this.mediator = mediator;
    }

    public MarcajeFileSystem(int idMarcaje, int idProducto, int idAula, Date timeStamp, TipoMarcaje tipo) {
        this.idMarcaje = idMarcaje;
        this.idProducto = idProducto;
        this.idAula = idAula;
        this.timeStamp = timeStamp;
        this.tipo = tipo;
    }

    public MarcajeFileSystem(int idProducto, int idAula, Date timeStamp, TipoMarcaje tipo) {
        this.idProducto = idProducto;
        this.idAula = idAula;
        this.timeStamp = timeStamp;
        this.tipo = tipo;
    }

    public int getIdMarcaje() {
        return idMarcaje;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getIdAula() {
        return idAula;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public TipoMarcaje getTipo() {
        return tipo;
    }

    @Override
    public boolean insertMarcaje(MarcajeInterface marcaje) {
        return mediator.addMarcaje(marcaje);
    }

    @Override
    public boolean updateMarcaje(MarcajeInterface marcaje) {
        ArrayList<MarcajeInterface> marcajes = (ArrayList<MarcajeInterface>) mediator.getMarcajes();
        for (MarcajeInterface marcaje1 : marcajes) {
            if (marcaje1.getIdMarcaje() == marcaje.getIdMarcaje()) {
                marcajes.remove(marcaje1);
                marcajes.add(marcaje);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMarcaje(int id) {
        ArrayList<MarcajeInterface> marcajes = (ArrayList<MarcajeInterface>) mediator.getMarcajes();
        for (MarcajeInterface marcaje : marcajes) {
            if (marcaje.getIdMarcaje() == id) {
                marcajes.remove(marcaje);
                return true;
            }
        }
        return false;
    }

    @Override
    public MarcajeInterface selectMarcaje(int id) {
        ArrayList<MarcajeInterface> marcajes = (ArrayList<MarcajeInterface>) mediator.getMarcajes();
        for (MarcajeInterface marcaje : marcajes) {
            if (marcaje.getIdMarcaje() == id) {
                return marcaje;
            }
        }
        return null;
    }

    @Override
    public ArrayList<MarcajeInterface> selectAllMarcajes() {
        return (ArrayList<MarcajeInterface>) mediator.getMarcajes();
    }


    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setTipo(TipoMarcaje tipo) {
        this.tipo = tipo;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public void setIdMarcaje(int idMarcaje) {
        this.idMarcaje = idMarcaje;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
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
