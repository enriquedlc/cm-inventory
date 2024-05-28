package com.cifpcm.inventory.models.marcaje.enums;

public enum TipoMarcaje {
    ENTRADA("ENTRADA"), SALIDA("SALIDA");

    private final String tipoMarcajeValue;

    private TipoMarcaje(String tipoMarcajeValue) {
        this.tipoMarcajeValue = tipoMarcajeValue;
    }

    public String getTipoMarcajeValue() {
        return tipoMarcajeValue;
    }

}
