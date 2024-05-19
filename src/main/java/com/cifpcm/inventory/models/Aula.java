package com.cifpcm.inventory.models;

public class Aula {
    private int idAula;
    private String numeracion;
    private String descripcion;
    private String direccionIp;

    public Aula() {
    }

    public Aula(int idAula, String numeracion, String descripcion, String direccionIp) {
        this.idAula = idAula;
        this.numeracion = numeracion;
        this.descripcion = descripcion;
        this.direccionIp = direccionIp;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    @Override
    public String toString() {
        return "Aula{" + "idAula=" + idAula + ", numeracion=" + numeracion + ", descripcion=" + descripcion + ", direccionIp=" + direccionIp + '}';
    }
}
