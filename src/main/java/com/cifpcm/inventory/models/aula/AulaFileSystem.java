package com.cifpcm.inventory.models.aula;

import com.cifpcm.inventory.mediator.Mediator;

import java.util.ArrayList;

public class AulaFileSystem implements AulaInterface {
    private int idAula;
    private  String numeracion;
    private String descripcion;
    private String ip;
    private final Mediator mediator;

    public AulaFileSystem(Mediator mediator) {
        this.mediator = mediator;
    }

    public AulaFileSystem(Mediator mediator, String numeracion, String descripcion, String ip) {
        this.mediator = mediator;
        this.numeracion = numeracion;
        this.descripcion = descripcion;
        this.ip = ip;
    }

    public AulaFileSystem(Mediator mediator, int idAula, String numeracion, String descripcion, String ip) {
        this.mediator = mediator;
        this.idAula = idAula;
        this.numeracion = numeracion;
        this.descripcion = descripcion;
        this.ip = ip;
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
    public boolean insertAula(AulaInterface aula) {
        mediator.addAula(aula);
        return true;
    }

    @Override
    public boolean updateAula(AulaInterface aula) {
        ArrayList<AulaInterface> aulas = (ArrayList<AulaInterface>) mediator.getAulas();
        for (AulaInterface aula1 : aulas) {
            if (aula1.getIdAula() == aula.getIdAula()) {
                aula1 = aula;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAula(int id) {
        ArrayList<AulaInterface> aulas = (ArrayList<AulaInterface>) mediator.getAulas();
        for (AulaInterface aula : aulas) {
            if (aula.getIdAula() == id) {
                aulas.remove(aula);
                return true;
            }
        }
        return false;
    }

    @Override
    public AulaInterface selectAula(int id) {
        ArrayList<AulaInterface> aulas = (ArrayList<AulaInterface>) mediator.getAulas();
        for (AulaInterface aula : aulas) {
            if (aula.getIdAula() == id) {
                return aula;
            }
        }
        return null;
    }

    @Override
    public ArrayList<AulaInterface> selectAllAulas() {
        return (ArrayList<AulaInterface>) mediator.getAulas();
    }

    @Override
    public String toString() {
        return "AulaFileSystem{" + "idAula=" + idAula + ", numeracion=" + numeracion + ", descripcion=" + descripcion + ", ip=" + ip + '}';
    }

}
