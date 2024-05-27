package com.cifpcm.inventory.models.aula;


public class Aula implements AulaInterface{
    private int idAula;
    private String numeracion;
    private String descripcion;
    private String ip;

    public Aula() {
    }

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

    public int getIdAula() {
        return idAula;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

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

